homeApp
		.config(function($translateProvider) {
			$translateProvider
					.translations(
							'en',
							{
								PROJECT_NAME : 'Board Games: Exchange',
								LOGIN : 'Login',
								REGISTRATION : 'Registration',
								CUR_RATING : 'Your rating is {{rating}}',
								NEEDED_RATING : 'You need {{neededRating}} to achieve next level',
								SEARCH : 'Search',
								WELCOME_BACK : 'Welcome back, ',
								EDIT_PROFILE : 'Edit profile',
								MESSAGES : 'Messages',
								USERS : 'Users',
								LOGOUT : 'Logout',
								ALL_GAMES : 'All games',
								EVENTS : 'Events',
								TOURNAMENTS : 'Tournaments',
								STATISTICS : 'Statistics',
								EDIT_PROFILE : 'Edit profile',
								USER_PROFILE : 'User profile',
								UPLOAD_AVATAR : 'Upload avatar',
								FIRST_NAME : 'First name:',
								LAST_NAME : 'Last name:',
								USERNAME : 'Username:',
								EMAIL : 'Email:',
								COUNTRY : 'Country:',
								CITY : 'City:',
								GENDER : 'Gender:',
								AGE : 'Age:',
								PHONE_NUMBER : 'Phone number:',
								SAVE_BUTTON : 'Save',
								CHANGE_PASSWORD : 'Change password',
								OLD_PASSWORD : 'Old password:',
								NEW_PASSWORD : 'New password:',
								CONFIRM_PASSWORD : 'Confirm password:',
								SAVE_PASSWORD_BUTTON : 'Save password',
								USERS : 'Users',
								USER_LAST_NAME : 'Last name: {{userLastName}}',
								USER_FIRST_NAME : 'First name: {{userFirstName}}',
								USER_GENDER : 'Gender: {{userGender}}',
								USER_AGE : 'Age: {{userAge}}',
								USER_LEVEL : 'Level: {{userLevel}}',
								USER_PHONE_NUMBER : 'Phone number: {{userPhoneNumber}}',
								USER_GAMES : 'Games user owns',
								USER_TOURNAMENTS : 'Users tournaments',
								USERNAME_TABLE : 'Username',
								EMAIL_TABLE : 'Email',
								COUNTRY_TABLE : 'Country',
								CITY_TABLE : 'City',
								CLOSE : 'Close',
								LOGINFORM_HEADER : 'LOGIN WITH USERNMAE AND PASSWORD',
								LOGINFORM_USERNAME : 'Enter Username',
								LOGINFORM_PASSWORD : 'Enter Password',
								LOGINFORM_ENTER : 'START',
								CANCEL : 'Cancel',
								DONT_HAVE_ACCOUNT : 'Don\'t have an account? Click here!',
								INCORRECT_LOGIN_OR_PASS : 'INCORRECT LOGIN OR PASSWORD',
								REGFORM_HEADER : 'REGISTRATION OF NEW USER',
								REGFORM_USERNAME : 'Username',
								REGFORM_FIRSTNAME : 'First Name',
								REGFORM_LASTNAME : 'Last Name',
								REGFORM_EMAIL : 'Email',
								REGFORM_PASSWORD : 'Password',
								REGFORM_CONFIRMPASSWORD : 'Confirm Password',
								REGFORM_GENDER : 'Gender',
								REGFORM_GENDER_MALE : 'Male',
								REGFORM_GENDER_FEMALE : 'Female',
								REGFORM_SIGNUP : 'Sign up',
								REGFORM_USERNAME_PLACEHOLDER : 'Enter Your Username. From 3 to 9 symbols required',
								REGFORM_FIRSTNAME_PLACEHOLDER : 'Enter Your Name',
								REGFORM_LASTNAME_PLACEHOLDER : 'Enter Your Last Name',
								REGFORM_EMAIL_PLACEHOLDER : 'Enter Your email address',
								REGFORM_PASSWORD_PLACEHOLDER : 'From 6 to 20 symbols. 1 Upper case and 1 number is required',
								REGFORM_CONFIRMPASSWORD_PLACEHOLDER : 'Please, confirm Your password',
								UNDER_VERIFICATION_HEADER : 'UNDER VERIFICATION',
								UNDER_VERIFICATION_FIRST : 'Your account is temporary unavailable due to its verification.',
								UNDER_VERIFICATION_SECOND : 'We have sent You email with confirmation link.',
								UNDER_VERIFICATION_THIRD : 'After You confirm Your registration You will be able to log in',
								ON_REGISTRATION_HEADER : 'SUCCESS REGISTRATION',
								ON_REGISTRATION_FIRST : 'Dear, User! We have sent you a message in order to verify Your email and confirm Your registration.',
								ON_REGISTRATION_SECOND : 'Keep in mind, that Your confirmation link will expire after 1 hour',
								ON_REGISTRATION_THIRD : 'After You confirm, You will be able to Sign in.',
								LOADING : 'Loading ...',
								USERINFO_PROCEED : 'Proceed to login page',
								USERINFO_SUCCESS : 'Your email was successfully comfirmed. Now You can login.',
								USERINFO_FAILURE : 'You already confirm your registration or your confirmation link was expired. Please, try to register one more time with different username and email',
								BAN_MODAL_HEADER : 'YOU\'VE BEEN BANNED',
								BAN_MODAL_FIRST : 'The access to your account was temporary disabled',
								BAN_MODAL_SECOND : 'Your account have been banned due to Your inappropriate behavior',
								BAN_MODAL_THIRD : 'If there is some mistake, please contact with the administration',
								CONTACT_WITH_ADMINS : 'Contact admins',
								CHANGES_SAVED : 'Changes saved',
								IMAGE_UPLOAD_FAILED : 'Failed to upload image. Try one more time',
								IMAGE_UPLOAD : 'Avatar uploaded',
								FOTO_ISNT_CHOOSED : 'You haven\'t choosed the file',
								USER_BAN : 'User with username {{username}} was banned',
								USER_UNBAN : 'User with username {{username}} was unbanned',
								OLD_PASSWORD_ANSWER : 'Sorry, but you typed wrong old password',
								NEW_PASSWORD_ANSWER : 'Sorry, but Your password must contain at least one lower case symbol, '
										+ 'one Upper case symbol, one number and be from 6 to 20 chars long',
								CONFIRM_PASSWORD_ANSWER : 'Sorry, but You must confirm Your password',
								INVALID_FIRST_OR_LAST_NAME:'Sorry, byt you typed incorrect data in first name or last name field',
								INVALID_AGE:'Sorry, byt you typed incorrect data in age field',
								INVALID_PHONE_NUMBER:'Sorry, byt you typed incorrect data in phone number field',
								TO_BAN:'Ban user',
								TO_UNBAN:'Unban user',
								STATE:'State',
								ACTIVE:'Active',
								BANNED:'Banned',
								SCORE: 'Score: {{rating}}',
								NOOB:'Level: NOOB',
								SKILLED:'Level: SKILLED',
								PRO:'Level: PRO',
								VETERAN:'Level: VETERAN',
								MASTER:'Level: MASTER',
								WICKED_SICK:'Level: WICKED_SICK',
								EXTRATERESTRIAL:'Level: EXTRATERESTRIAL',
								GODLIKE:'Level: GODLIKE',
								SEND_LETTER:'Send letter to administrator',
								SEND:'Send',
								LETTER_ABOUT_UNBAN_RECEIVED: 'We received Your letter. Wait for the answer.',
								Ukraine:'Ukraine',
								Poland:'Poland',
								Cherkasy:'Cherkasy',
								Chernihiv:'Chernihiv',
								Chernivtsi:'Chernivtsi',
								Dnipro:'Dnipro',
								Donetsk:'Donetsk',
								Ivano_Frankivsk:'Ivano-Frankivsk',
								Kharkiv:'Kharkiv',
								Kherson:'Kherson',
								Khmelnytskyi:'Khmelnytskyi',
								Kyiv:'Kyiv',
								Kirovohrad:'Kirovohrad',
								Luhansk:'Luhansk',
								Lviv:'Lviv',
								Mykolaiv:'Mykolaiv',
								Odessa:'Odessa',
								Poltava:'Poltava',
								Rivne:'Rivne',
								Sumy:'Sumy',
								Ternopil:'Ternopil',
								Vinnytsia:'Vinnytsia',
								Lutsk:'Lutsk',
								Uzhhorod:'Uzhhorod',
								Zaporizhia:'Zaporizhia',
								Zhytomyr:'Zhytomyr',
								Warsaw:'Warsaw',
								Wroclaw:'Wroclaw',
								Cracow:'Cracow',
								Wloclawek:'Wloclawek',
								Gdansk:'Gdansk',
								Sopot:'Sopot',
								Gdynia:'Gdynia',
								Lublin:'Lublin',
								Zabrze:'Zabrze',
								Olsztyn:'Olsztyn',
								Tychy:'Tychy',
								Rzeszow:'Rzeszow',
								Bydgoszcz:'Bydgoszcz',
								Rybnik:'Rybnik',
								Szczecin:'Szczecin',
								Katowice:'Katowice',
								Bialystok:'Bialystok',
								Sosnowiec:'Sosnowiec',
								Bytom:'Bytom',
								NO_COUNTRY:'No country selected',
								NO_CITY:'No city selected',
								MODERATE_EVENT : 'Moderate Events',
								FOTO_SIZE : 'The file size must be less than 5MB',
								NOTIFICATION : 'Notification',
								FRIEND : 'Friends',
								MY_FRIEND : 'My Friends',
								FRIEND_REQUES : 'Friend request',
								FRIEND_RESPONCE : 'Friend responce',
								FRIEND_MESSAGE : 'Find your friends in our Application',
								FRIEND_NOTE : 'Fiend new friends',
								DELETE_FRIEND_MESSAGE : 'Do you realy want to dalete from your friends list ',
								CONFIRM_YES : 'Yes',
								CONFIRM_CANCEL : 'Cancel',
								CONFIRM_CLOSE : 'Close',
								NOTE_TO_WRITE_MESSAGE : 'Write a message',
								NAME_OF_GAME : 'Name',
								CATEGORY : 'Category',
								DESCRIPTION : 'Description',
								RULES : 'Rules',
								MIN_PLAYERS : 'Min players',
								MAX_PLAYERS : 'Max players',
								EDITION : 'Edition',
								YEAR_OF_PRODUCTION : 'Year of production',
								MY_GAMES : 'My games',
								COMMENTS : 'Comments',
								DELETE : 'Delete',
								SHARED : 'My shared',
								BORROWED : 'Borrowed',
								MYGAMES : 'My games',
								GAMES : 'Games',
								ALL_GAMES: 'All games',
								GAME_NAME: 'Name',
								GAME_NAME_SEMICOLUMN: 'Name: ',
								GAME_CATEGORY: 'Category',
								GAME_INSTANCES_COUNT: 'Instances count',
								GAME_AVAILABLE_INSTANCES_COUNT: 'Available',
								GAME_RATING: 'Game rating',
								GAME_RATING_OF_USER: 'Your rating: ',
								GENERAL_GAME_RATING: 'Ganeral rating: ',
								OWNERS_OF_GAME: 'Owners',
								USER_OF_USERGAME_USERNAME: 'Username',
								GAME_EDITION: 'Edition',
								GAME_YEAR_OF_PRODUCTION: 'Year of production',
								AVAILABILITY: 'Availability',
								GAME_DETAILS : 'Game details',
								YEAR_OF_PRODUCTION_SEMI : 'Year of production: ',
								EDITION_SEMI : 'Edition: ',
								MAX_MIN_PLAYERS_SEMI : 'Max/Min players: ',
								STATUS_SEMI : 'Status: ',
								DESCRIPTION_SEMI : 'Description: ',
								RULES_SEMI : 'Rules: ',
								EDIT : 'Edit',
								MAKE_GAME_AVIALABLE : 'Make game available',
								OPEN_GAME_FOR_SHARING : 'Open game for sharing',
								ENTER_PERIOD_SEMI : 'Enter period of exchange: ',
								MAKE_AVAILABLE : 'Make available',
								KEEP_IT_FOR_ME : 'Keep it for now',
								LETS_MAKE_A_DEAL : 'Let`s make a deal',
								SEND_REQUEST : 'Send request',
								DAYS_SEMI : 'Days: ',
								MESSAGE_FOR_OWNER_SEMI : 'Message for owner: ',
								PROPOSE : 'Propose',
								ASK_OWNER : 'Ask owner',
								SELECT_WHAT_YOU_WANT_TO_PROPOSE_SEMI : 'Select what you want to propose',
								ADD_GAME : 'Add game',
								ASKS_YOU_TO_SHARE : 'Asks you to share',
								OFFERS_SEMI : 'Offers: ',
								ACCEPT : 'Accept',
								DECLINE : 'Decline',
								RETURN : 'Return',
								RETURN_THE_GAME : 'Return the game',
								YOUR_FEEDBACK : 'Your feedback',
								GIVE_BACK_TO_OWNER : 'Give back to owner',
								DAYS_REMAINS_SEMI : 'Days remains: ',
								RETURN : 'Return',
								RETURN_THE_GAME : 'Return the game',
								YOUR_FEEDBACK : 'Your feedback',
								GIVE_BACK_TO_OWNER : 'Give ack to owner',
								male:'male',
								female:'female',
								REQUIRED_FIELDS: 'Fields marked with \"*\" are required',
								USERNAME_DUPLICATE: 'This Username is already taken. Please choose another one',
								INVALID_USERNAME: 'Username must be from 3 to 9 symbols long',
								EMAIL_DUPLICATE: 'This email address is already in use. Please choose another on',
								INVALID_EMAIL: 'You\'ve enetered not valid email address',
								INVALID_PASSWORD: 'Password mus be from 6 to 20 and containes at least 1 number and 1 upper case letter',
								NOT_CONFIRMED_PASSWORD: 'You must confirm Your password',
								PROFILE: 'Profile',
								SIGN_OUT: 'Sign out',
								MODERATE_USERS: 'Moserate users',
								MODERATE_EVENTS: 'Moderate events',
								HI: 'Hi,',
								MENU: 'MENU',
								SEARCH: 'Search ...',
								UPCOMING_EVENTS: 'UPCOMING EVENTS',
								LOCATION: 'Location:',
								NAME_EVENT: 'Name',
								DATE_EVENT: 'Date',
								DESCRIPTION_EVENT: 'Description:',
								EVENT_DETAILS: 'Event Details',
								EVENT_NAME_DETAILS: 'Event name:',
								SUB: 'Subscribe',
								UNSUB: 'Unsubsubscribe',
								DESC: 'Description',
								CREATE_NEW_EVENT: 'Create New Event',
								ALL_FIELDS_REQ: 'all fields are required',
								ADD_NEW: 'ADD NEW',
								UPD_EVENT: 'Update event',
								CANCEL_EVENT: 'Cancel event',
								EDIT_EVENT: 'Edit Event',
								TOURNAMENT_NAME : 'Tournament name',
								USER_CREATOR : 'User creator',
								DATES : 'Date',
								MY_TOURNAMENTS : 'My Tournaments'
							});
			$translateProvider
					.translations(
							'ua',
							{
								PROJECT_NAME : 'Настільні ігри: Обмін',
								LOGIN : 'Логін',
								REGISTRATION : 'Реєстрація',
								CUR_RATING : 'Ваш рейтинг {{rating}}',
								NEEDED_RATING : 'Вам потрібно набрати {{neededRating}} для отримання наступного звання',
								SEARCH : 'Пошук',
								WELCOME_BACK : 'З поверненням,',
								EDIT_PROFILE : 'Редагувати профіль',
								MESSAGES : 'Листи',
								USERS : 'Користувачі',
								LOGOUT : 'Вийти',
								ALL_GAMES : 'Всі ігри',
								EVENTS : 'Події',
								TOURNAMENTS : 'Турніри',
								STATISTICS : 'Статистика',
								EDIT_PROFILE : 'Редагувати профіль',
								USER_PROFILE : 'Сторінка користувача',
								UPLOAD_AVATAR : 'Завантажити аву',
								FIRST_NAME : 'Ім`я:',
								LAST_NAME : 'Прізвище:',
								USERNAME : 'Логін:',
								EMAIL : 'Електронна пошта:',
								COUNTRY : 'Країна:',
								CITY : 'Місто:',
								GENDER : 'Стать:',
								AGE : 'Вік:',
								PHONE_NUMBER : 'Телефон:',
								SAVE_BUTTON : 'Зберегти',
								CHANGE_PASSWORD : 'Змінити пароль',
								OLD_PASSWORD : 'Старий пароль:',
								NEW_PASSWORD : 'Новий пароль:',
								CONFIRM_PASSWORD : 'Підтвердити пароль:',
								SAVE_PASSWORD_BUTTON : 'Зберегти пароль',
								USERS : 'Користувачі',
								USER_LAST_NAME : 'Прізвище: {{userLastName}}',
								USER_FIRST_NAME : 'Ім`я: {{userFirstName}}',
								USER_GENDER : 'Стать: {{userGender}}',
								USER_AGE : 'Вік: {{userAge}}',
								USER_LEVEL : 'Звання: {{userLevel}}',
								USER_PHONE_NUMBER : 'Телефон: {{userPhoneNumber}}',
								USER_GAMES : 'Ігри користувача',
								USER_TOURNAMENTS : 'Турніри',
								USERNAME_TABLE : 'Логін',
								EMAIL_TABLE : 'Електронна пошта',
								COUNTRY_TABLE : 'Країна',
								CITY_TABLE : 'Місто',
								CLOSE : 'Закрити',
								LOGINFORM_HEADER : 'ВХІД ЗА ЛОГІНОМ ТА ПАРОЛЕМ',
								LOGINFORM_USERNAME : 'Введіть Ваш логін',
								LOGINFORM_PASSWORD : 'Введіть Ваш пароль',
								LOGINFORM_ENTER : 'ВХІД',
								CANCEL : 'Скасувати',
								DONT_HAVE_ACCOUNT : 'Досі не зареєстровані? Вам сюди!',
								INCORRECT_LOGIN_OR_PASS : 'НЕВІРНИЙ ЛОГІН АБО ПАРОЛЬ',
								REGFORM_HEADER : 'РЕЄСТРАЦІЯ НОВГО КОРИСТУВАЧА',
								REGFORM_USERNAME : 'Логін',
								REGFORM_FIRSTNAME : 'Ваше ім\'я',
								REGFORM_LASTNAME : 'Ваше прізвище',
								REGFORM_EMAIL : 'Пошта',
								REGFORM_PASSWORD : 'Пароль',
								REGFORM_CONFIRMPASSWORD : 'Підтвердіть пароль',
								REGFORM_GENDER : 'Стать',
								REGFORM_GENDER_MALE : 'Чоловік',
								REGFORM_GENDER_FEMALE : 'Жінка',
								REGFORM_SIGNUP : 'Реєстрація',
								REGFORM_USERNAME_PLACEHOLDER : 'Введіть Ваш логін. Повинен містити від 3 до 9 символів',
								REGFORM_FIRSTNAME_PLACEHOLDER : 'Введіть Ваше ім\'я',
								REGFORM_LASTNAME_PLACEHOLDER : 'Введіть ваше прізвище',
								REGFORM_EMAIL_PLACEHOLDER : 'Введіть адресу вашої електронної скриньки',
								REGFORM_PASSWORD_PLACEHOLDER : 'Від 6 до 20 символів. Хоча б 1 велика літера і 1 цифра',
								REGFORM_CONFIRMPASSWORD_PLACEHOLDER : 'Підтрвердіть Ваш пароль, будь ласка',
								UNDER_VERIFICATION_HEADER : 'ПЕРЕВІРКА РЕЄСТРАЦІЇ',
								UNDER_VERIFICATION_FIRST : 'Ваш акаунт тимчасово недоступний, оскільки ми перевіряємо Ваші дані.',
								UNDER_VERIFICATION_SECOND : 'Ми надіслали Вам лінк для підтвердження реєстрації на пошту.',
								UNDER_VERIFICATION_THIRD : 'Після того, як Ви підтвердите свою реєстрацію, Ви зможете увійти.',
								ON_REGISTRATION_HEADER : 'УСПІШНА РЕЄСТРАЦІЯ',
								ON_REGISTRATION_FIRST : 'Шановний Користувач! Ми надіслали Вам листа з метою підтвердити справжність вашої електронної скриньки, а також, щоб Ви підтвердили свою реєстрацію',
								ON_REGISTRATION_SECOND : 'Будь ласка, зверніть увагу, що лінк підтвердження буде доступний лише протягом години',
								ON_REGISTRATION_THIRD : 'Після того як Ви підтвердите свою реєстрацію Ви зможете увійти',
								LOADING : 'Завантаження ...',
								USERINFO_PROCEED : 'Перейти до головної сторінки',
								USERINFO_SUCCESS : 'Ваша електронна адреса була успішно підтверджена. Тепер Ви можете увійти',
								USERINFO_FAILURE : 'Ви вже підтвердили вашу електронну адресу або термін дії лінка підтвердження сплив. Якщо Ви не можете увійти, Вам слід повторно зареєструватись.',
								BAN_MODAL_HEADER : 'ВАС БУЛО ЗАБЛОКОВАНО',
								BAN_MODAL_FIRST : 'Доступ до Вашого акаунту було тимчасово заблоковано',
								BAN_MODAL_SECOND : 'Вас було заблоковано через Вашу неприпустиму поведінку',
								BAN_MODAL_THIRD : 'Якщо виникла якась помилка, будь ласка, зконтактуйтесь з адміністрацією',
								CONTACT_WITH_ADMINS : 'Написати адміну',
								CHANGES_SAVED : 'Зміни збережено',
								IMAGE_UPLOAD_FAILED : 'Фотографію не завантажено. Спробуйте ще раз',
								IMAGE_UPLOAD : 'Фотографію завантажено',
								FOTO_ISNT_CHOOSED : 'Ви не вибрали файл',
								USER_BAN : 'Користувача з логіном {{username}} було заблоковано',
								USER_UNBAN : 'Користувача з логіном {{username}} було розблоковано',
								OLD_PASSWORD_ANSWER : 'Вибачте, але Ви ввели невірний старий пароль',
								NEW_PASSWORD_ANSWER : 'Вибачте, але Ваш пароль повинен містити хоча б одну маленьку літеру,'
										+ 'одну велику літеру, одну цифру та бути від 6 до 20 символів',
								CONFIRM_PASSWORD_ANSWER : 'Вибачте, але ви маєте підтвердити Ваш пароль',
								INVALID_FIRST_OR_LAST_NAME:'Вибачте, але Ви ввели некоректну інформацію у поля ім\'я або прізвище',
								INVALID_AGE:'Вибачте, але Ви ввели некоректну інформацію у полe з зазначенням Вашого віку',
								INVALID_PHONE_NUMBER:'Вибачте, але Ви ввели некоректну інформацію у полe з номером телефону',
								TO_BAN:'Заблокувати юзера',
								TO_UNBAN:'Розблокувати юзера',
								STATE:'Стан',
								ACTIVE:'Активний',
								BANNED:'Заблокований',
								SCORE: 'Рейтинг: {{rating}}',
								NOOB:'Звання: НОВАЧОК',
								SKILLED:'Звання: БУВАЛИЙ',
								PRO:'Звання: ПРОФІ',
								VETERAN:'Звання: ВЕТЕРАН',
								MASTER:'Звання: МАСТЕР',
								WICKED_SICK:'Звання: НЕРЕАЛЬНИЙ',
								EXTRATERESTRIAL:'Звання: ПОЗАЗЕМНИЙ',
								GODLIKE:'Звання: БОГ',
								SEND_LETTER:'Відправити лист адміністратору',
								SEND:'Відправити',
								LETTER_ABOUT_UNBAN_RECEIVED: 'Ми отримали Ваше повідомлення. Очікуйте на відповідь.',
								Ukraine:'Україна',
								Poland:'Польща',
								Cherkasy:'Черкаси',
								Chernihiv:'Чернігів',
								Chernivtsi:'Чернівці',
								Dnipro:'Дніпро',
								Donetsk:'Донецьк',
								Ivano_Frankivsk:'Івано-Франківськ',
								Kharkiv:'Харків',
								Kherson:'Херсон',
								Khmelnytskyi:'Хмельницький',
								Kyiv:'Київ',
								Kirovohrad:'Кіровоград',
								Luhansk:'Луганськ',
								Lviv:'Львів',
								Mykolaiv:'Миколаїв',
								Odessa:'Одеса',
								Poltava:'Полтава',
								Rivne:'Рівне',
								Sumy:'Суми',
								Ternopil:'Тернопіль',
								Vinnytsia:'Вінниця',
								Lutsk:'Луцьк',
								Uzhhorod:'Ужгород',
								Zaporizhia:'Запоріжжя',
								Zhytomyr:'Житомир',
								Warsaw:'Варшава',
								Wroclaw:'Вроцлав',
								Cracow:'Краків',
								Wloclawek:'Влоцлавек',
								Gdansk:'Гданськ',
								Sopot:'Сопот',
								Gdynia:'Гдиня',
								Lublin:'Люблін',
								Zabrze:'Забже',
								Olsztyn:'Ольштин',
								Tychy:'Тичи',
								Rzeszow:'Жешув',
								Bydgoszcz:'Будогож',
								Rybnik:'Рибнік',
								Szczecin:'Щєчін',
								Katowice:'Катовіце',
								Bialystok:'Бялисток',
								Sosnowiec:'Сосновєц',
								Bytom:'Битом',
								NO_COUNTRY:'Країну не вибрано',
								NO_CITY:'Місто не вибрано',
								MODERATE_EVENT : 'Модерувати Події',
								FOTO_SIZE : 'Розмір файла повинен становити не більше 5MБ',
								NOTIFICATION : 'Сповіщення',
								FRIEND : 'Друзі',
								MY_FRIEND : 'Мої друзі',
								FRIEND_REQUES : 'Підписники',
								FRIEND_RESPONCE : 'Заявки у друзі',
								FRIEND_MESSAGE : 'Знайди друзів! Буде весело!',
								FRIEND_NOTE : 'Знайди нових друзів',
								DELETE_FRIEND_MESSAGE : 'Чи дійсно Ви хочете видалити із списку друзів ',
								CONFIRM_YES : 'Так',
								CONFIRM_CANCEL : 'Ні',
								CONFIRM_CLOSE : 'Закрити',
								NOTE_TO_WRITE_MESSAGE : 'Напишіть повідомлення',
								NAME_OF_GAME :'Назва гри',
								CATEGORY : 'Категорія',
								DESCRIPTION : 'Опис',
								RULES : 'Правила',
								MIN_PLAYERS : 'Мін. кількість гравців',
								MAX_PLAYERS : 'Макс. кількість гравців',
								EDITION : 'Випуск',
								YEAR_OF_PRODUCTION : 'Рік випуску',
								MY_GAMES : 'Мої Ігри',
								COMMENTS : 'Коментарі',
								DELETE : 'Видалити',
								SHARED : 'Я позичив',
								BORROWED : 'Позичені',
								MYGAMES : 'Мої ігри',
								GAMES : 'Ігри',
								ALL_GAMES : 'Всі ігри',
								GAME_NAME : 'Назва',
								GAME_NAME_SEMICOLUMN: 'Назва: ',
								GAME_CATEGORY : 'Категорія',
								GAME_INSTANCES_COUNT : 'Кількість екземплярів',
								GAME_AVAILABLE_INSTANCES_COUNT : 'Відкрито для обміну',
								GAME_RATING : 'Рейтинг',
								GAME_RATING_OF_USER : 'Рейтинг користувача: ',
								GENERAL_GAME_RATING : 'Загальний рейтинг: ',
								OWNERS_OF_GAME : 'Власники',
								USER_OF_USERGAME_USERNAME: 'Логін',
								GAME_DETAILS : 'Деталі гри',
								GAME_EDITION : 'Видання',
								GAME_YEAR_OF_PRODUCTION : 'Рік виробництва',
								AVAILABILITY : 'Статус',
								GAME_DETAILS : 'Деталі гри',
								YEAR_OF_PRODUCTION_SEMI : 'Рік виробництва: ',
								EDITION_SEMI : 'Видання: ',
								MAX_MIN_PLAYERS_SEMI : 'Макс/Мін гравців: ',
								STATUS_SEMI : 'Статус: ',
								DESCRIPTION_SEMI : 'Опис: ',
								RULES_SEMI : 'Правила: ',
								EDIT : 'Редагувати',
								MAKE_GAME_AVIALABLE : 'Зробити гру доступною',
								OPEN_GAME_FOR_SHARING : 'Відкрити гру для обміну',
								ENTER_PERIOD_SEMI : 'Введіть період обміну: ',
								MAKE_AVAILABLE : 'Зробити доступним',
								KEEP_IT_FOR_ME : 'Залишити для себе',
								LETS_MAKE_A_DEAL : 'Запропонувати обмін',
								SEND_REQUEST : 'Відправити запит',
								DAYS_SEMI : 'Днів: ',
								MESSAGE_FOR_OWNER_SEMI : 'Повідомлення для власника: ',
								PROPOSE : 'Запропонувати гру',
								ASK_OWNER : 'Відправити запит власнику',
								SELECT_WHAT_YOU_WANT_TO_PROPOSE_SEMI : 'Виберіть що ви хочете запропонувати',
								ADD_GAME : 'Додати гру',
								ASKS_YOU_TO_SHARE : 'Пропонує обмін',
								OFFERS_SEMI : 'Пропозиція: ',
								ACCEPT : 'Прийняти',
								DECLINE : 'Відхилити',
								RETURN : 'Повернути',
								RETURN_THE_GAME : 'Повернення гри',
								YOUR_FEEDBACK : 'Ваш відгук',
								GIVE_BACK_TO_OWNER : 'Повернути власнику',
								DAYS_REMAINS_SEMI : 'Днів залишилось: ',
								RETURN : 'Повернути',
								RETURN_THE_GAME : 'Повернення гри',
								YOUR_FEEDBACK : 'Ваш відгук',
								GIVE_BACK_TO_OWNER : 'Повернути власнику',
								male:'чоловік',
								female:'жінка',
								REQUIRED_FIELDS: 'Поля позначені \"*\" є обов\'язковими до заповнення',
								USERNAME_DUPLICATE: 'Даний логін уже використовується іншим користувачем. Виберіть інший, будь ласка',
								INVALID_USERNAME: 'Логін повинен містити не менше 3, але не більше 9 символів',
								EMAIL_DUPLICATE: 'Дана адреса електронної пошти вже використовується. Вкажіть іншу, будь ласка',
								INVALID_EMAIL: 'Ви ввели невірний адрес електронної скриньки',
								INVALID_PASSWORD: 'Пароль повинен бути від 6 до 20 символів у довжину і містити хоча б 1 велику літеру і 1 цифру',
								NOT_CONFIRMED_PASSWORD: 'Ви повинні підтвердити свій пароль',
								PROFILE: 'Профіль',
								SIGN_OUT: 'Вихід',
								MODERATE_USERS: 'Модерувати користувачів',
								MODERATE_EVENTS: 'Модерувати події',
								HI: 'Привіт,',
								MENU: 'МЕНЮ',
								SEARCH: 'Пошук ...',
								UPCOMING_EVENTS: 'ПРИЙДЕШНІ ПОДІЇ',
								LOCATION: 'Локація:',
								NAME_EVENT: 'Назва',
								DATE_EVENT: 'Дата події',
								DESCRIPTION_EVENT: 'Опис події:',
								EVENT_DETAILS: 'Деталі події',
								EVENT_NAME_DETAILS: 'Назва події:',
								SUB: 'Підписатись',
								UNSUB: 'Відписатись',
								DESC: 'Опис події',
								CREATE_NEW_EVENT: 'Створити нову подію',
								ALL_FIELDS_REQ: 'усі поля є обов\'\язковими',
								ADD_NEW: 'Додати',
								UPD_EVENT: 'Поновити подію',
								CANCEL_EVENT: 'Скасувати подію',
								EDIT_EVENT: 'Редагувати подію',
								TOURNAMENT_NAME : 'Назва турніру',
								USER_CREATOR : 'Власник',
								DATES : 'Дата',
								MY_TOURNAMENTS : 'Мої турніри'

							});
			$translateProvider.preferredLanguage('en');
			$translateProvider.useSanitizeValueStrategy('escape');
			$translateProvider.useCookieStorage();
		});

homeApp.controller('localizationController', function($scope, $translate) {
	$scope.changeLanguage = function(key) {
		$translate.use(key);
	};
});