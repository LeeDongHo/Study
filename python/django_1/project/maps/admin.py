from django.contrib import admin
from maps.models import Question, Choice

# admin.site에 등록
admin.site.register(Question)
admin.site.register(Choice)
# Register your models here.
