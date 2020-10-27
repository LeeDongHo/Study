import requests

headers = {
    'Authorization': 'KakaoAK 1396e864c2d756b2134c36063d2e9e0c',
}

params = {
    'category_group_code':'FD6',
    'radius':'2000',
}
class category_parser:
    def __init__(self):
        pass

    def get_json(self, x, y, page_number):
        params['x'] = str(x)
        params['y'] = str(y)
        params['page'] = page_number
        return requests.get('https://dapi.kakao.com/v2/local/search/category.json', headers=headers, params=params).json()

    def get_genre(self, x, y):
        category = set()
        for n in range(1, 10):
            result = self.get_json(x,y,n)
            if result['meta']['is_end'] is True:
                break
            for i in result['documents']:
                category.add(i['category_name'].split(' > ')[1])
        return category



        

test = category_parser()
result = test.get_genre(126.671662349336,37.4096254840933)

# 컨셉이 점심 메뉴 결정이기 때문에 술집 제거
if '술집' in result:
    result.remove('술집')
for i in result:
    print(i)