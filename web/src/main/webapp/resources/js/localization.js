homeApp.config(function ($translateProvider) {
	  $translateProvider.translations('en', {
	    PROJECT_NAME:'Board Games: Exchange',
	    LOGIN: 'Login',
	    REGISTRATION: 'Registration',
	    COUNTRY:'Country',
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
	    EDIT_PROFILE:'Edit profile'
	  });
	  $translateProvider.translations('ua', {
		PROJECT_NAME:'Настільні ігри: Обмін',
		LOGIN: 'Логін',
		REGISTRATION: 'Реєстрація',
		COUNTRY:'Країна',
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
	    EDIT_PROFILE:'Редагувати профіль'
	  });
	  $translateProvider.preferredLanguage('en');
	  $translateProvider.useSanitizeValueStrategy('escape');
});

homeApp.controller('localizationController', function($scope, $translate) {
	$scope.changeLanguage = function (key) {
	    $translate.use(key);
	};
});