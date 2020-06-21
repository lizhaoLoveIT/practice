import urllib.request
import urllib.parse
import string
import ssl


def get_method_params(name):
    # ssl._create_default_https_context = ssl._create_unverified_context
    url = "http://www.sogou.com/web?query="
    url = url + name
    print(url)
    encode_url = urllib.parse.quote(url, safe=string.printable)
    print(encode_url)
    response = urllib.request.urlopen(encode_url)
    print(response)
# UnicodeEncodeError: 'ascii' codec can't encode characters in position 10-11: ordinal not in range(128)
# python 只识别英文
    data = response.read()
    print(data)
    with open("baidu_meinv.html", "w", encoding="utf-8") as f:
        f.write(data.decode("utf-8"))

get_method_params("美女")