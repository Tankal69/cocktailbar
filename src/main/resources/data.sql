-- Optional: clean out old data before re-seeding
DELETE FROM cocktail_ingredient;
DELETE FROM cocktail;
DELETE FROM ingredient;

-- Ingredients
INSERT INTO ingredient (name) VALUES
                                  ('vodka'),
                                  ('dry_martini'),
                                  ('gin'),
                                  ('rum'),
                                  ('tequila'),
                                  ('triple sec'),
                                  ('lime juice'),
                                  ('simple syrup'),
                                  ('cola'),
                                  ('lemon juice'),
                                  ('orange juice'),
                                  ('cranberry juice'),
                                  ('mint leaves'),
                                  ('sugar'),
                                  ('club soda'),
                                  ('ice');

-- Cocktails
INSERT INTO cocktail (name, description) VALUES
                                             ('Margarita', 'A classic Mexican cocktail with tequila, lime, and orange liqueur.'),
                                             ('Mojito', 'A refreshing Cuban drink made with rum, lime, mint, and soda water.'),
                                             ('Cosmopolitan', 'A stylish cocktail with vodka, cranberry juice, lime, and triple sec.'),
                                             ('Long Island Iced Tea', 'A strong cocktail with multiple spirits and cola.'),
                                             ('Screwdriver', 'A simple and popular drink with vodka and orange juice.');

-- Cocktail-Ingredient Relationships using subqueries

-- Margarita
INSERT INTO cocktail_ingredient (cocktail_id, ingredient_id, quantity)
VALUES (
           (SELECT id FROM cocktail WHERE name = 'Margarita'),
           (SELECT id FROM ingredient WHERE name = 'tequila'),
           '2 oz'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Margarita'),
           (SELECT id FROM ingredient WHERE name = 'lime juice'),
           '1 oz'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Margarita'),
           (SELECT id FROM ingredient WHERE name = 'triple sec'),
           '1 oz'
       );

-- Mojito
INSERT INTO cocktail_ingredient (cocktail_id, ingredient_id, quantity)
VALUES (
           (SELECT id FROM cocktail WHERE name = 'Mojito'),
           (SELECT id FROM ingredient WHERE name = 'rum'),
           '2 oz'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Mojito'),
           (SELECT id FROM ingredient WHERE name = 'lime juice'),
           '1 oz'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Mojito'),
           (SELECT id FROM ingredient WHERE name = 'mint leaves'),
           '6 leaves'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Mojito'),
           (SELECT id FROM ingredient WHERE name = 'sugar'),
           '2 tsp'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Mojito'),
           (SELECT id FROM ingredient WHERE name = 'club soda'),
           'top up'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Mojito'),
           (SELECT id FROM ingredient WHERE name = 'ice'),
           'ice'
       );

-- Cosmopolitan
INSERT INTO cocktail_ingredient (cocktail_id, ingredient_id, quantity)
VALUES (
           (SELECT id FROM cocktail WHERE name = 'Cosmopolitan'),
           (SELECT id FROM ingredient WHERE name = 'vodka'),
           '1.5 oz'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Cosmopolitan'),
           (SELECT id FROM ingredient WHERE name = 'cranberry juice'),
           '1 oz'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Cosmopolitan'),
           (SELECT id FROM ingredient WHERE name = 'triple sec'),
           '1 oz'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Cosmopolitan'),
           (SELECT id FROM ingredient WHERE name = 'lime juice'),
           '0.5 oz'
       );

-- Long Island Iced Tea
INSERT INTO cocktail_ingredient (cocktail_id, ingredient_id, quantity)
VALUES (
           (SELECT id FROM cocktail WHERE name = 'Long Island Iced Tea'),
           (SELECT id FROM ingredient WHERE name = 'vodka'),
           '0.5 oz'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Long Island Iced Tea'),
           (SELECT id FROM ingredient WHERE name = 'gin'),
           '0.5 oz'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Long Island Iced Tea'),
           (SELECT id FROM ingredient WHERE name = 'rum'),
           '0.5 oz'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Long Island Iced Tea'),
           (SELECT id FROM ingredient WHERE name = 'tequila'),
           '0.5 oz'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Long Island Iced Tea'),
           (SELECT id FROM ingredient WHERE name = 'triple sec'),
           '0.5 oz'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Long Island Iced Tea'),
           (SELECT id FROM ingredient WHERE name = 'lemon juice'),
           '0.5 oz'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Long Island Iced Tea'),
           (SELECT id FROM ingredient WHERE name = 'cola'),
           'top up'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Long Island Iced Tea'),
           (SELECT id FROM ingredient WHERE name = 'ice'),
           'ice'
       );

-- Screwdriver
INSERT INTO cocktail_ingredient (cocktail_id, ingredient_id, quantity)
VALUES (
           (SELECT id FROM cocktail WHERE name = 'Screwdriver'),
           (SELECT id FROM ingredient WHERE name = 'vodka'),
           '2 oz'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Screwdriver'),
           (SELECT id FROM ingredient WHERE name = 'orange juice'),
           '4 oz'
       ), (
           (SELECT id FROM cocktail WHERE name = 'Screwdriver'),
           (SELECT id FROM ingredient WHERE name = 'ice'),
           'ice'
       );
