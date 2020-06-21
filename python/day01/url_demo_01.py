import urllib.request

def load_data():
    # 为什么 https 会报错
    url = "http://www.baidu.com"
    #get 的请求
    #http请求
    response = urllib.request.urlopen(url)
    # print(response)
    data = response.read()
    print(data)
    str_data = data.decode("utf-8")
    # print(str_data)
    # 将数据写入文件
    with open("baidu.html", "w", encoding="utf-8") as f:
        f.write(str_data)

load_data()