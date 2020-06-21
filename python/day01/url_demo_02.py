import urllib.request
import urllib.parse
import string
# import ssl

def demo03():
    # ssl._create_default_https_context = ssl._create_unverified_context
    url = "http://www.sogou.com/web?"
    name = {
        "query" : "美女"
    }
    url = url + urllib.parse.urlencode(name)
    print(url)
    url = urllib.parse.quote(url, safe=string.printable)
    response = urllib.request.urlopen(url)
    with open("baidu.html", "w", encoding="utf-8") as f:
        f.write(response.read().decode("utf-8"))

demo03()