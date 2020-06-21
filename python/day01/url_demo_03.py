import urllib.request
import urllib.parse


def demo_03():
    url = "http://baidu.com"

    headers = {
        "User-Agent" : "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36"
    }

    # request = urllib.request.Request(url, headers=headers)
    request = urllib.request.Request(url)
    request.add_header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36")

    print(request.headers)
    response = urllib.request.urlopen(request)
    #获取到完整的url
    final_url = request.get_full_url()

    #响应头
    # print(response.headers)
    #获取请求头的信息(所有的头的信息)
    # request_headers = request.headers
    # print(request_headers)
    #(2)第二种方式打印headers的信息
    #注意点:首字母需要大写,其他字母都小写
    request_headers = request.get_header("User-agent")

    with open("baidu.html", "w", encoding="utf-8") as f:
        f.write(response.read().decode("utf-8"))

demo_03()