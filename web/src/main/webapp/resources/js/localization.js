homeApp.config(function ($translateProvider) {
	  $translateProvider.translations('en', {
	    PROJECT_NAME:'Board Games: Exchange',
	    LOGIN: 'Login',
	    REGISTRATION: 'Registration',	    
	    USER_LEVEL:'Your level is {{level}}',
	    CUR_RATING:'Your rating is {{rating}}',
	    NEEDED_RATING:'You need {{neededRating}} to achieve next level',
	    SEARCH:'Search',
	    WELCOME_BACK:'Welcome back, ',
	    EDIT_PROFILE:'Edit profile',
	    MESSAGES:'Messages',
	    USERS:'Users',
	    LOGOUT:'Logout',
	    ALL_GAMES:'All games',
	    EVENTS:'Events',
	    TOURNAMENTS:'Tournaments',
	    STATISTICS:'Statistics',
	    EDIT_PROFILE:'Edit profile',
	    USER_PROFILE:'User profile',
	    UPLOAD_AVATAR:'upload avatar',
	    FIRST_NAME:'First name:',
	    LAST_NAME:'Last name:',
	    USERNAME:'Username:',
	    EMAIL:'Email:',
	    COUNTRY:'Country:',
	    CITY:'City:',
	    GENDER:'Gender:',
	    AGE:'Age:',
	    PHONE_NUMBER:'Phone number:',
	    SAVE_BUTTON:'Save',
	    CHANGE_PASSWORD:'Change password',
	    OLD_PASSWORD:'Old password:',
	    NEW_PASSWORD:'New password:',
	    CONFIRM_PASSWORD:'Confirm password:',
	    SAVE_PASSWORD_BUTTON:'Save password'
	  });
	  $translateProvider.translations('ua', {
		PROJECT_NAME:'Настільні ігри: Обмін',
		LOGIN: 'Логін',
		REGISTRATION: 'Реєстрація',
		USER_LEVEL:'Ваше звання {{level}}',
	    CUR_RATING:'Ваш рейтинг {{rating}}',
	    NEEDED_RATING:'Вам потрібно набрати {{neededRating}} для отримання наступного звання',
	    SEARCH:'Пошук',
	    WELCOME_BACK:'З поверненням,',
	    EDIT_PROFILE:'Редагувати профіль',
	    MESSAGES:'Листи',
	    USERS:'Користувачі',
	    LOGOUT:'Вийти',
	    ALL_GAMES:'Всі ігри',
	    EVENTS:'Події',
	    TOURNAMENTS:'Турніри',
	    STATISTICS:'Статистика',
	    EDIT_PROFILE:'Редагувати профіль',
	    USER_PROFILE:'Сторінка юзера',
	    UPLOAD_AVATAR:'завантажити аву',
	    FIRST_NAME:'Ім`я:',
	    LAST_NAME:'Прізвище:',
	    USERNAME:'Логін:',
	    EMAIL:'Електронна пошта:',
	    COUNTRY:'Країна:',
	    CITY:'Місто:',
	    GENDER:'Стать:',
	    AGE:'Вік:',
	    PHONE_NUMBER:'Телефон:',
	    SAVE_BUTTON:'Зберегти',
	    CHANGE_PASSWORD:'Змінити пароль',
	    OLD_PASSWORD:'Старий пароль:',
	    NEW_PASSWORD:'Новий пароль:',
	    CONFIRM_PASSWORD:'Підтвердити пароль:',
	    SAVE_PASSWORD_BUTTON:'Зберегти пароль'
	  });
	  $translateProvider.preferredLanguage('en');
	  $translateProvider.useSanitizeValueStrategy('escape');
});

homeApp.controller('localizationController', function($scope, $translate) {
	$scope.changeLanguage = function (key) {
	    $translate.use(key);
	};
});