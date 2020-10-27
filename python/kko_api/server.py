import requests

headers = {
    'Authorization': 'KakaoAK 1396e864c2d756b2134c36063d2e9e0c',
}

params = { 
    'query':'양식',
    'category_group_code':'FD6',
    "x":"126.671662349336",
    "y":"37.4096254840933",
    'radius':'2000',
    'page':'1'
    }

response = requests.get('https://dapi.kakao.com/v2/local/search/keyword.json', headers=headers, params=params).json()
print(response)

params['page'] = '2'
response = requests.get('https://dapi.kakao.com/v2/local/search/category.json', headers=headers, params=params)
print(response.json())