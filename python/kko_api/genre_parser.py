import requests

headers = {
    'Authorization': 'KakaoAK 1396e864c2d756b2134c36063d2e9e0c',
}

params = {
    'category_group_code':'FD6',
    'radius':'2000',
}
class genre_parser:
    def __init__(self):
        pass

    def get_json(self, genre, x, y, page_number):
        # Request parameter 작성
        params['query'] = genre
        params['x'] = str(x)
        params['y'] = str(y)
        params['page'] = page_number
        return requests.get('https://dapi.kakao.com/v2/local/search/keyword.json', headers=headers, params=params).json()

    # genre에 해당하는 음식점들의 키워드 반환
    # ex) 양식 -> 피자, 햄버거, 이탈리안
    def get_keyword(self, genre, x, y):
        keyword = set()

        for n in range(1, 10):
            result = self.get_json(genre,x,y,n)
            if result['meta']['is_end'] is True:
                break
            for i in result['documents']:
                temp = i['category_name'].split(' > ')
                # 카테고리 설정이 세세하게 되어 있지 않은 점포 데이터가 존재
                if len(temp) > 2:
                    keyword.add(temp[2])
                else:    
                    keyword.add(temp[-1])
        return keyword

    # keyword에 대한 장소 정보 반환
    #   서가앤쿡 -> 장소 데이터
    def get_place_data(self, keyword, x, y):
        result = []
        for n in range(1, 10):
            result = self.get_json(keyword,x,y,n)
            if result['meta']['is_end'] is True:
                break
            for i in result['documents']:
                result.append(i)
        return result



        

test = genre_parser()
result = test.get_keyword('양식',126.671662349336,37.4096254840933)
for i in result:
    print(i)

print("장소 데이터 return")

print(test.get_place_data('서가앤쿡',126.671662349336,37.4096254840933))