-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2025-08-13 15:17:46.854

-- tables
-- Table: Cocktail
CREATE TABLE cocktail (
    id int GENERATED ALWAYS AS IDENTITY NOT NULL,
    name varchar(20) GENERATED ALWAYS AS IDENTITY NOT NULL,
    description varchar(20) GENERATED ALWAYS AS IDENTITY NOT NULL,
    cocktail_ingredient_id int  NOT NULL,
    CONSTRAINT Cocktail_pk PRIMARY KEY (id)
);

-- Table: cocktail_ingredient
CREATE TABLE cocktail_ingredient (
    id int GENERATED ALWAYS AS IDENTITY NOT NULL,
    cocktail_id int  NOT NULL,
    ingredient_id int GENERATED ALWAYS AS IDENTITY NOT NULL,
    quantity varchar(20) GENERATED ALWAYS AS IDENTITY NOT NULL,
    CONSTRAINT cocktail_ingredient_pk PRIMARY KEY (id)
);

-- Table: indgredient
CREATE TABLE indgredient (
    id int GENERATED ALWAYS AS IDENTITY NOT NULL,
    cocktail_ingredient_id int  NOT NULL,
    CONSTRAINT indgredient_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: Cocktail_cocktail_ingredient (table: Cocktail)
ALTER TABLE cocktail ADD CONSTRAINT Cocktail_cocktail_ingredient
    FOREIGN KEY (cocktail_ingredient_id)
    REFERENCES cocktail_ingredient (id);

-- Reference: indgredient_cocktail_ingredient (table: indgredient)
ALTER TABLE indgredient ADD CONSTRAINT indgredient_cocktail_ingredient
    FOREIGN KEY (cocktail_ingredient_id)
    REFERENCES cocktail_ingredient (id);

-- End of file.

