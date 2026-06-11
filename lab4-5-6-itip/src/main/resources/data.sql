-- Вставка пользователей только если они не существуют
INSERT INTO users (name, email, password, role, phone, device_token, telegram_chat_id, created_at)
SELECT 'Админ Админов', 'admin@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_ADMIN', '+79169999999', 'token_admin', '@admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'admin@example.com');

INSERT INTO users (name, email, password, role, phone, device_token, telegram_chat_id, created_at)
SELECT 'Модератор Системы', 'moderator@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_ADMIN', '+79168888888', 'token_moder', '@moder', NOW()
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'moderator@example.com');

INSERT INTO users (name, email, password, role, phone, device_token, telegram_chat_id, created_at)
SELECT 'Александр Иванов', 'alex@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '+79161234567', 'token_alex', '@alex', NOW()
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'alex@example.com');

INSERT INTO users (name, email, password, role, phone, device_token, telegram_chat_id, created_at)
SELECT 'Мария Петрова', 'maria@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '+79162345678', 'token_maria', '@maria', NOW()
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'maria@example.com');

INSERT INTO users (name, email, password, role, phone, device_token, telegram_chat_id, created_at)
SELECT 'Дмитрий Сидоров', 'dmitry@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '+79163456789', 'token_dmitry', '@dmitry', NOW()
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'dmitry@example.com');

INSERT INTO users (name, email, password, role, phone, device_token, telegram_chat_id, created_at)
SELECT 'Елена Козлова', 'elena@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '+79164567890', 'token_elena', '@elena', NOW()
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'elena@example.com');

INSERT INTO users (name, email, password, role, phone, device_token, telegram_chat_id, created_at)
SELECT 'Никита Новиков', 'nikita@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '+79165678901', 'token_nikita', '@nikita', NOW()
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'nikita@example.com');

INSERT INTO users (name, email, password, role, phone, device_token, telegram_chat_id, created_at)
SELECT 'Ольга Морозова', 'olga@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '+79166789012', 'token_olga', '@olga', NOW()
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'olga@example.com');

INSERT INTO users (name, email, password, role, phone, device_token, telegram_chat_id, created_at)
SELECT 'Артем Волков', 'artem@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '+79167890123', 'token_artem', '@artem', NOW()
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'artem@example.com');

INSERT INTO users (name, email, password, role, phone, device_token, telegram_chat_id, created_at)
SELECT 'Наталья Соколова', 'natalia@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ROLE_USER', '+79168901234', 'token_natalia', '@natalia', NOW()
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'natalia@example.com');

-- Уведомления (вставляем только если уведомление с таким заголовком не существует)
INSERT INTO notifications (title, message, channel, status, created_at, sent_at, recipient_id)
SELECT 'Приветственное уведомление', 'Добро пожаловать в систему!', 'EMAIL', 'SENT', NOW(), NOW(), 1
WHERE NOT EXISTS (SELECT 1 FROM notifications WHERE title = 'Приветственное уведомление' AND recipient_id = 1);

INSERT INTO notifications (title, message, channel, status, created_at, sent_at, recipient_id)
SELECT 'Новое сообщение', 'У вас новое сообщение', 'PUSH', 'SENT', NOW(), NOW(), 1
WHERE NOT EXISTS (SELECT 1 FROM notifications WHERE title = 'Новое сообщение' AND recipient_id = 1);

INSERT INTO notifications (title, message, channel, status, created_at, sent_at, recipient_id)
SELECT 'Обновление профиля', 'Пожалуйста, обновите профиль', 'EMAIL', 'PENDING', NOW(), NULL, 2
WHERE NOT EXISTS (SELECT 1 FROM notifications WHERE title = 'Обновление профиля' AND recipient_id = 2);