from django.shortcuts import render, get_object_or_404
from django.http import HttpResponseRedirect, HttpResponse
from django.urls import reverse
from maps.models import Question, Choice

# Django
# form django.core.urlresolvers -> python 2.x version => django.urls
# Create your views here.
# MVC 모델에서 C의 영역

def index(request):
    # Question 테이블 객체에서 pub_date 컬럼의 역순으로 정렬하여 5개의 최근 Question 객체를 가져옴
    # 파이썬에서 objects는 동적으로 추가하기 때문에 vscode는 모름.
    # models에 obejcts=models.Manager() 추가로 해결 가능
    latest_question_list = Question.objects.all().order_by('-pub_date')[:5] 
    print(latest_question_list)
    context = {'latest_question_list': latest_question_list}
    print(context)
    # render() 는 템플릿 파일인 maps/index.html에 context 변수를 적용하여 사용자에게 보여줄 
    # 최종 HTML 텍스트를 만들고 이를 담아서 HttpResponse 객체를 반환
    return render(request, 'maps/index.html', context)

def detail(request, question_id):
    question = get_object_or_404(Question, pk=question_id)
    return render(request, 'maps/detail.html', {'question':question})

def results(request, question_id):
    question = get_object_or_404(Question, pk=question_id)
    return render(request, 'maps/results.html', {'question':question})

def vote(request, question_id):
    question = get_object_or_404(Question, pk=question_id)
    try:
        selected_choice = question.choice_set.get(pk=request.POST['choice'])
    # 폼의 POST 데이터에서 'choice'라는 키가 없으면 KeyError except 발생. 
    # 검색 조건에 맞는 객체가 없으면 DoesNotExist 발생
    except (KeyError, Choice.DoesNotExist):
        # 설문 투표 폼을 다시 보여준다
        return render(request, 'maps/detail.html', { 
            'question' : question,
            'error_message':"You didn't select a choice.",
        })
    else:
        selected_choice.votes += 1
        selected_choice.save()
        # POST 데이터를 정상적으로 처리하였으면,
        # 항상 HttpResponseRedirect를 반환하여 리다이렉션 처리함
        # reverse : URL 패턴으로 부터 URL String 추출
        return HttpResponseRedirect(reverse('maps:results', args=(question.id,)))
    return