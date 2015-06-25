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

import java.util.LinkedList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SM;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.message.BasicHeader;

/**
 * Copy of the {@link BrowserCompatSpec} that should support the GAE issue
 * 
 * @see http://code.google.com/p/googleappengine/issues/detail?id=3379
 * 
 * @since 4.0
 */
@NotThreadSafe
// superclass is @NotThreadSafe
class GaeCompartibilityCookieSpec extends BrowserCompatSpec
{
	/** Default constructor */
	public GaeCompartibilityCookieSpec()
	{
		super();
	}
	
	private int getNextInterestingTokenPos(final String source, final int startPos)
	{
		final int length = source.length();
		for (int curPos = startPos; curPos < length; ++curPos)
		{
			final char curChar = source.charAt(curPos);
			if (curChar == '=' 
				|| curChar == ','
				|| curChar == ';'
				|| curChar == '"'
				)
			{
				return curPos;
			}
		}
		return -1;
	}
	
	private int getNextPos(final String source, final int startPos)
	{
		final int length = source.length();
		if (startPos >= length)
		{
			return -1;
		}
		
		int pairStartPos = startPos;
		
		while (pairStartPos < length)
		{
			int workPos = getNextInterestingTokenPos(source, pairStartPos);
			if (workPos < 0)
			{
				return length;
			}
			final char workChar = source.charAt(workPos);
			
			if (workChar == '"')
			{
				pairStartPos = source.indexOf('"', workPos + 1);
				continue;
			}
			
			if (workChar == ';')
			{
				pairStartPos = workPos + 1;
				continue;
			}
			
			if (workChar == '=')
			{
				//it's expires, skip until next ';'
				workPos = source.indexOf(';', workPos + 1);
				if (workPos < 0)
				{
					return length;
				}
				pairStartPos = workPos + 1;
				continue;
			}

			if (workChar == ',')
			{
				return workPos; // found comma at left side, so it's a separator
			}
		}
			
		return length;
	}
	
	private List<Header> splitHeader(final Header header)
	{
		List<Header> splitHeadersList = new LinkedList<Header>();

		final String headerName = header.getName();
		final String headerValue = header.getValue();
		
		int curPos = 0;
		int nextPos = -1;
		while ((nextPos = getNextPos(headerValue, curPos)) != -1)
		{
			splitHeadersList.add(new BasicHeader(headerName, headerValue.substring(curPos, nextPos)));
			curPos = nextPos + 1;
		}
		
		//here
		return splitHeadersList;
	}

	@Override
	public List<Cookie> parse(final Header header, final CookieOrigin origin) throws MalformedCookieException
	{
		if ( header == null )
		{
			throw new IllegalArgumentException("Header may not be null");
		}
		if ( origin == null )
		{
			throw new IllegalArgumentException("Cookie origin may not be null");
		}
		String headername = header.getName();
		if ( !headername.equalsIgnoreCase(SM.SET_COOKIE) )
		{
			throw new MalformedCookieException("Unrecognized cookie header '" + header.toString() + "'");
		}
		
		List<Header> splitHeadersList = splitHeader(header);
		
		List<Cookie> resultCookieList = new LinkedList<Cookie>();
		for (Header splitHeader : splitHeadersList)
		{
			resultCookieList.addAll(super.parse(splitHeader, origin));
		}
		
		return resultCookieList;
	}

	@Override
	public String toString()
	{
		return "gae compatibility";
	}
}
