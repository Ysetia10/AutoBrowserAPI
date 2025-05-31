API to remotely control a browser — open/close browsers, open tabs, and clear cache — all without UI.

Features
Open/close browser via API

Open specific tabs by URL

Clear cache programmatically

API Endpoints
Endpoint	Method	Description
/browser/open	POST	Open browser
/browser/close	POST	Close browser
/tab/open	POST	Open tab with URL
/tab/clear-cache	POST	Clear cache of a tab

Example:

curl -X POST http://localhost:3000/tab/open -d '{"url":"https://example.com"}' -H "Content-Type: application/json"
