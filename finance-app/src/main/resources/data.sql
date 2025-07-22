INSERT INTO users(name, balance) VALUES ('Ali', 1000), ('Vali', 500);

INSERT INTO transaction(user_id, amount, timestamp)
VALUES 
  (1, 200.0, NOW()),
  (2, -100.0, NOW());
