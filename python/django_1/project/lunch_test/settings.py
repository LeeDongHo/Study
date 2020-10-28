"""
Django settings for lunch_test project.

Generated by 'django-admin startproject' using Django 3.1.2.

For more information on this file, see
https://docs.djangoproject.com/en/3.1/topics/settings/

For the full list of settings and their values, see
https://docs.djangoproject.com/en/3.1/ref/settings/
"""

from pathlib import Path

# Build paths inside the project like this: BASE_DIR / 'subdir'.
BASE_DIR = Path(__file__).resolve().parent.parent


# Quick-start development settings - unsuitable for production
# See https://docs.djangoproject.com/en/3.1/howto/deployment/checklist/

# SECURITY WARNING: keep the secret key used in production secret!
SECRET_KEY = 'agm5or&g%4w3jz=r2yj1$6oz%wk5)*(q$&&aoeh07z3#loqzgn'

# SECURITY WARNING: don't run with debug turned on in production!
# Treu : 개발 모드, False : 운영 모드.
DEBUG = True

# 운영모드인 경우 ALLOWD_HOSTS에 반드시 서버의 IP나 도메인을 지정
# 개발모드인 경우 값 지정 X -> ['localhost','127.0.0.1']로 간주
ALLOWED_HOSTS = []


# Application definition
# 프로젝트에 포함되는 어플리케이션들을 모두 등록 -> maps 등록
# 어플리케이션 등록은 모듈명 'maps'만 등록해도 되지만 
#   어플리케이션의 설정 클래스로 등록하는 것이 더 정확한 방법
#   maps 폴더의 apps.py 파일에 MapsConfig 라고 정의
#   => maps.apps.MapsConfig
INSTALLED_APPS = [
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    'maps.apps.MapsConfig',         # 추가
]

MIDDLEWARE = [
    'django.middleware.security.SecurityMiddleware',
    'django.contrib.sessions.middleware.SessionMiddleware',
    'django.middleware.common.CommonMiddleware',
    'django.middleware.csrf.CsrfViewMiddleware',
    'django.contrib.auth.middleware.AuthenticationMiddleware',
    'django.contrib.messages.middleware.MessageMiddleware',
    'django.middleware.clickjacking.XFrameOptionsMiddleware',
]

ROOT_URLCONF = 'lunch_test.urls'

TEMPLATES = [
    {
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': [],
        'APP_DIRS': True,
        'OPTIONS': {
            'context_processors': [
                'django.template.context_processors.debug',
                'django.template.context_processors.request',
                'django.contrib.auth.context_processors.auth',
                'django.contrib.messages.context_processors.messages',
            ],
        },
    },
]

WSGI_APPLICATION = 'lunch_test.wsgi.application'


# Database
# https://docs.djangoproject.com/en/3.1/ref/settings/#databases
# default : SQLite3

DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.sqlite3',
        'NAME': BASE_DIR / 'db.sqlite3',
    }
}


# Password validation
# https://docs.djangoproject.com/en/3.1/ref/settings/#auth-password-validators

AUTH_PASSWORD_VALIDATORS = [
    {
        'NAME': 'django.contrib.auth.password_validation.UserAttributeSimilarityValidator',
    },
    {
        'NAME': 'django.contrib.auth.password_validation.MinimumLengthValidator',
    },
    {
        'NAME': 'django.contrib.auth.password_validation.CommonPasswordValidator',
    },
    {
        'NAME': 'django.contrib.auth.password_validation.NumericPasswordValidator',
    },
]


# Internationalization
# https://docs.djangoproject.com/en/3.1/topics/i18n/
# USE_TZ = True : 장고가 알아서 시간대 조정 
#   DB에는 UTC, 템플릿 처리 시 TIME_ZONE에 지정된 시간대를 반영해 처리

LANGUAGE_CODE = 'en-us'

# TIME_ZONE = 'UTC
TIME_ZONE = 'Asia/Seoul'

USE_I18N = True

USE_L10N = True

USE_TZ = True


# Static files (CSS, JavaScript, Images)
# https://docs.djangoproject.com/en/3.1/howto/static-files/

STATIC_URL = '/static/'
