*** History about index/landing page ***

2Share started out with /parse/index.html as the original landing page but now, it is no longer a main landing page.
Thus it has been renamed as index1.html.

However, it has been re-used for user registration page (i.e. /parse/index1.html or /parse/index-c.html for RI site).

So both of them (index1.html or /parse/index-c.html) are still in use, and they makes use of requirejs's main-index1.js to load index1.js.

The current landing page is /ui/index.html, with the help of loader main-index.js (loading index.js).

Interesting enough, /html/index.js is still shared by both the new landing page (/ui/index.html) as well as the old one (/html/index1.js).

*** References ***

https://www.parse.com/docs/js_guide#fbusers

https://developers.facebook.com/apps