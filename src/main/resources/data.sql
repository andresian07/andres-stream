-- Inserción de las 20 películas (Si el título ya existe, no hace nada y evita el error)
INSERT INTO andres_stream_peliculas (titulo, duration, genero, fecha_estreno, calificacion, estado) VALUES
                                                                                                        ('Into the Wild', 148, 'Aventura', '2007-09-21', 8.10, 'A'),
                                                                                                        ('Your Name', 106, 'Anime', '2016-08-26', 8.40, 'A'),
                                                                                                        ('Spirited Away', 125, 'Anime', '2001-07-20', 8.60, 'A'),
                                                                                                        ('Weathering with You', 112, 'Anime', '2019-07-19', 7.50, 'A'),
                                                                                                        ('Princess Mononoke', 134, 'Anime', '1997-07-12', 8.40, 'A'),
                                                                                                        ('Inception', 148, 'Ciencia Ficción', '2010-07-16', 8.80, 'A'),
                                                                                                        ('The Dark Knight', 152, 'Acción', '2008-07-18', 9.00, 'A'),
                                                                                                        ('Pulp Fiction', 154, 'Crimen', '1994-10-14', 8.90, 'A'),
                                                                                                        ('The Matrix', 136, 'Ciencia Ficción', '1999-03-31', 8.70, 'A'),
                                                                                                        ('Interstellar', 169, 'Ciencia Ficción', '2014-11-07', 8.60, 'A'),
                                                                                                        ('Gladiator', 155, 'Acción', '2000-05-05', 8.50, 'A'),
                                                                                                        ('The Shawshank Redemption', 142, 'Drama', '1994-09-23', 9.30, 'A'),
                                                                                                        ('Parasite', 132, 'Suspenso', '2019-05-30', 8.50, 'A'),
                                                                                                        ('Whiplash', 106, 'Drama', '2014-10-10', 8.50, 'A'),
                                                                                                        ('The Prestige', 130, 'Misterio', '2006-10-20', 8.50, 'A'),
                                                                                                        ('Django Unchained', 165, 'Western', '2012-12-25', 8.40, 'A'),
                                                                                                        ('Spider-Man: Into the Spider-Verse', 117, 'Animación', '2018-12-14', 8.40, 'A'),
                                                                                                        ('The Departed', 151, 'Suspenso', '2006-10-06', 8.50, 'A'),
                                                                                                        ('Shutter Island', 138, 'Misterio', '2010-02-19', 8.20, 'A'),
                                                                                                        ('The Truman Show', 103, 'Drama', '1998-06-05', 8.10, 'A')
    ON CONFLICT (titulo) DO NOTHING;