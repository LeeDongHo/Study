"""lunch_test URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""

# 예시 - 실제는 maps.urls.py를 작성
from django.contrib import admin
from django.urls import path, include
#from maps import views

# 패턴 매칭은 위 부터 아래로 내려간다.
urlpatterns = [
    path('admin/', admin.site.urls),    # admin 사이트를 사용하기 위해선 항상 이렇게 정의해야함.
    path('maps/', include('maps.urls')),
    #path('maps/', views.index, name='index'),
    #path('maps/<int:question_id>/', views.detail, name='detail'),
    #path('maps/<int:question_id>/result/', views.results, name='results'),
    #path('maps/<int:question_id>/vote/', views.vote, name='vote'),
]

# path(route, view, kwargs, name)
#   route : 필수o : URL 패턴을 표현하는 문자열 == URL String
#   view : 필수o : URL String이 매칭되면 호출되는 뷰 함수. HttpRequest 객체와 URL String에서 추출된 항목이 함수 인자로 전달
#   kwargs : 필수x : URL String에서 추출된 항목 외 추가적인 인자를 뷰 함수에 전달할 때, 파이썬 사전 타입으로 인자를 정의
#   name : 필수x : 각 URL 패턴별로 이름 부여. -> 템플릿 파일에서 이름 많이 사용