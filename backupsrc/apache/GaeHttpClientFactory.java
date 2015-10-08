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

import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.esxx.js.protocol.GAEConnectionManager;


public class GaeHttpClientFactory
{
	/**
	 * @return an Apache HttpClient properly set up for usage with GAE
	 */
	public static AbstractHttpClient createHttpClient()
	{
		final DefaultHttpClient httpClient = new DefaultHttpClient(new GAEConnectionManager(), new BasicHttpParams());
		httpClient.setRedirectHandler(new CorrectRedirectHandler());
		httpClient.getCookieSpecs().register(GaeCompartibilityCookieSpecFactory.POLICY_NAME, new GaeCompartibilityCookieSpecFactory());
		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, GaeCompartibilityCookieSpecFactory.POLICY_NAME);

		return httpClient;
	}
}
