INSERT INTO app_user (email, password, admin) VALUES
                                                  ('a@a.com', '$2a$10$5WEsvVInsp51juqbyGg.iuYdOjqzTCLRSN0P8SueniFMdousb2Dyi', 1),
                                                  ('b@b.com', '$2a$10$5WEsvVInsp51juqbyGg.iuYdOjqzTCLRSN0P8SueniFMdousb2Dyi', 0);

-- Ajoute les produits à la suite :
INSERT INTO produit (nom, url_image, prix) VALUES
                                               ('PlayStation 5 Pro', 'https://images.example.com/ps5pro.jpg', 799.99),
                                               ('iPhone 15 Pro Max 256Go', 'https://images.example.com/iphone15.jpg', 1479.00),
                                               ('Cafetière à grains DeLonghi', 'https://images.example.com/delonghi.jpg', 389.50),
                                               ('Lego Star Wars Millenium Falcon', 'https://images.example.com/lego-falcon.jpg', 849.99),
                                               ('Écran PC Gaming 32" Odyssey OLED', 'https://images.example.com/screen-samsung.jpg', 1199.00),
                                               ('Enceinte Bluetooth JBL Flip 6', 'https://images.example.com/jbl-flip6.jpg', 129.99),
                                               ('Trottinette Électrique Xiaomi Pro 4', 'https://images.example.com/xiaomi-4pro.jpg', 649.00),
                                               ('Casque Audio Sony WH-1000XM5', 'https://images.example.com/sony-xm5.jpg', 329.00),
                                               ('Montre connectée Apple Watch Series 9', 'https://images.example.com/apple-watch.jpg', 449.00),
                                               ('Aspirateur Balai Dyson V15 Detect', 'https://images.example.com/dyson-v15.jpg', 699.99),
                                               ('Jeu de société Carcassonne', 'https://images.example.com/carcassonne.jpg', 34.90),
                                               ('Canapé d''angle convertible Nordik', 'https://images.example.com/canape.jpg', 899.00);
