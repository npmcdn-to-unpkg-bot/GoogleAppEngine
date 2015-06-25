/*

Copyright 2011 Mikhail Barg, Andrey Grigoriev. All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are
permitted provided that the following conditions are met:

   1. Redistributions of source code must retain the above copyright notice, this list of
      conditions and the following disclaimer.

   2. Redistributions in binary form must reproduce the above copyright notice, this list
      of conditions and the following disclaimer in the documentation and/or other materials
      provided with the distribution.

THIS SOFTWARE IS PROVIDED BY MIKHAIL BARG AND ANDREY GIGORIEV ``AS IS'' AND ANY EXPRESS OR IMPLIED
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL MIKHAIL BARG, ANDREY GIGORIEV OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those of the
authors and should not be interpreted as representing official policies, either expressed
or implied, of Mikhail Barg or Andrey Grigoriev.

*/

package teamthree.tvnow.apache;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

/**
 * Override {@link DefaultRedirectHandler} so that it redirects the POST
 * requests as well
 * 
 * TODO: The problem is that {@link org.apache.http.impl.client.HttpRedirect} supports only 
 * PUT and GET, so redirecting POST will not work anyway..
 * 
 * So we either need to get back to {@link DefaultRedirectHandler} and give up on redirects, or 
 * go further fixing HttpRedirect.
 * 
 * @see http://hc.apache.org/httpcomponents-client-ga/tutorial/html/httpagent.html#d4e1049
 * 
 * 
 * @author Mike
 * 
 */
@Immutable
class CorrectRedirectHandler extends DefaultRedirectHandler
{
	public CorrectRedirectHandler()
	{
		super();
	}

	public boolean isRedirectRequested(final HttpResponse response, final HttpContext context)
	{
		if ( response == null )
		{
			throw new IllegalArgumentException("HTTP response may not be null");
		}

		int statusCode = response.getStatusLine().getStatusCode();
		switch ( statusCode )
		{
		case HttpStatus.SC_MOVED_TEMPORARILY:
		case HttpStatus.SC_MOVED_PERMANENTLY:
		case HttpStatus.SC_TEMPORARY_REDIRECT:
			HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
			String method = request.getRequestLine().getMethod();
			return method.equalsIgnoreCase(HttpPost.METHOD_NAME) // This one is missing in the Default handler
					|| method.equalsIgnoreCase(HttpGet.METHOD_NAME) 
					|| method.equalsIgnoreCase(HttpHead.METHOD_NAME);
		default:
			return super.isRedirectRequested(response, context);
		}
	}

}
