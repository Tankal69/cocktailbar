
-- Table: ingredient
CREATE TABLE ingredient (
                            id INT GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY,
                            name VARCHAR(50) NOT NULL
);


-- Table: cocktail
CREATE TABLE cocktail (
                          id INT GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY,
                          name VARCHAR(50) NOT NULL,
                          description VARCHAR(255) NOT NULL
);

-- Table: cocktail_ingredient (join table)
CREATE TABLE cocktail_ingredient (
                                     id INT GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY,
                                     cocktail_id INT NOT NULL,
                                     ingredient_id INT NOT NULL,
                                     quantity VARCHAR(50) NOT NULL,
                                     FOREIGN KEY (cocktail_id) REFERENCES cocktail(id) ON DELETE CASCADE,
                                     FOREIGN KEY (ingredient_id) REFERENCES ingredient(id) ON DELETE CASCADE
);

