-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2025-08-13 15:17:46.854

-- Tables

-- Table: cocktail
CREATE TABLE cocktail (
                          id INT GENERATED ALWAYS AS IDENTITY NOT NULL,
                          name VARCHAR(20) NOT NULL,
                          description VARCHAR(20) NOT NULL,
                          cocktail_ingredient_id INT NOT NULL,
                          CONSTRAINT cocktail_pk PRIMARY KEY (id)
);

-- Table: cocktail_ingredient
CREATE TABLE cocktail_ingredient (
                                     id INT GENERATED ALWAYS AS IDENTITY NOT NULL,
                                     cocktail_id INT NOT NULL,
                                     ingredient_id INT NOT NULL,
                                     quantity VARCHAR(20) NOT NULL,
                                     CONSTRAINT cocktail_ingredient_pk PRIMARY KEY (id)
);

-- Table: ingredient (fixed typo: was 'indgredient')
CREATE TABLE ingredient (
                            id INT GENERATED ALWAYS AS IDENTITY NOT NULL,
                            cocktail_ingredient_id INT NOT NULL,
                            CONSTRAINT ingredient_pk PRIMARY KEY (id)
);

-- Foreign keys

-- Reference: cocktail -> cocktail_ingredient
ALTER TABLE cocktail ADD CONSTRAINT cocktail_cocktail_ingredient
    FOREIGN KEY (cocktail_ingredient_id)
        REFERENCES cocktail_ingredient (id);

-- Reference: ingredient -> cocktail_ingredient
ALTER TABLE ingredient ADD CONSTRAINT ingredient_cocktail_ingredient
    FOREIGN KEY (cocktail_ingredient_id)
        REFERENCES cocktail_ingredient (id);
