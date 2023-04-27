USE MusicShop;

INSERT INTO singer (singerID, singerName, singerDesciption) VALUES
('SNG001', 'Adele', 'Adele Laurie Blue Adkins is an English singer-songwriter. She is one of the most successful and acclaimed female artists of the last decade, known for her powerful vocals and emotional ballads.'),
('SNG002', 'Ed Sheeran', 'Ed Sheeran is an English singer-songwriter. He is known for his soulful ballads and acoustic guitar-based pop songs. He has won multiple awards and his music has topped charts all over the world.'),
('SNG003', 'Beyoncé', 'Beyoncé Giselle Knowles-Carter is an American singer, songwriter, actress, and producer. She is one of the best-selling music artists of all time, with over 200 million records sold worldwide.'),
('SNG004', 'Taylor Swift', 'Taylor Swift is an American singer-songwriter. She is known for her narrative songwriting and has won numerous awards, including 11 Grammy Awards. She is also known for her philanthropy and advocacy work.'),
('SNG005', 'Bruno Mars', 'Bruno Mars is an American singer, songwriter, and record producer. He is known for his funk, soul, and R&B-infused pop music, and has won multiple awards, including 11 Grammy Awards.'),
('SNG006', 'Lady Gaga', 'Lady Gaga is an American singer, songwriter, and actress. She is known for her eclectic and often provocative work, and has won multiple awards, including 12 Grammy Awards.'),
('SNG007', 'Justin Bieber', 'Justin Bieber is a Canadian singer, songwriter, and actor. He is known for his pop and R&B-influenced music, and has won numerous awards, including a Grammy Award.'),
('SNG008', 'Rihanna', 'Robyn Rihanna Fenty is a Barbadian singer, actress, and businesswoman. She is one of the best-selling music artists of all time, with over 250 million records sold worldwide. She has won numerous awards, including 9 Grammy Awards.'),
('SNG009', 'Drake', 'Drake is a Canadian rapper, singer, songwriter, and actor. He is known for his introspective lyrics and melodic beats, and has won multiple awards, including 4 Grammy Awards.'),
('SNG010', 'Katy Perry', 'Katy Perry is an American singer, songwriter, and television judge. She is known for her catchy pop songs and colorful music videos, and has won numerous awards, including 5 MTV Video Music Awards.');

INSERT INTO genre (genreID, genreName) VALUES
('GEN001', 'Pop'),
('GEN002', 'Rock'),
('GEN003', 'Hip hop'),
('GEN004', 'Electronic'),
('GEN005', 'Jazz'),
('GEN006', 'Classical'),
('GEN007', 'Folk'),
('GEN008', 'R&B'),
('GEN009', 'Country'),
('GEN010', 'Reggae');

INSERT INTO album (albumID, genreID, singerID, thumbnail, numberOfSongs, albumDescription, price) VALUES
('ALB001', 'GEN001', 'SNG001', NULL, 14, 'Chromatica is the sixth studio album by American singer Lady Gaga. It was released on May 29, 2020, by Streamline and Interscope Records. Chromatica is a dance-pop, synth-pop, and electropop album, incorporating genres like house, disco, and techno. The album features guest appearances from Ariana Grande, Elton John, and Blackpink.', 12.99),
('ALB002', 'GEN001', 'SNG002', NULL, 16, '÷ is the third studio album by English singer-songwriter Ed Sheeran. It was released on 3 March 2017 through Asylum Records and Atlantic Records. The album contains a diverse range of music, including the genres pop, folk, hip hop, soul, and Irish folk. The album features the hit singles "Shape of You", "Castle on the Hill", and "Galway Girl".', 11.99),
('ALB003', 'GEN002', 'SNG002', NULL, 12, 'No.6 Collaborations Project is the fourth studio album by English singer-songwriter Ed Sheeran. It was released on 12 July 2019 by Asylum Records and Atlantic Records. The album features collaborations with a variety of artists, including Justin Bieber, Bruno Mars, and Cardi B.', 9.99),
('ALB004', 'GEN003', 'SNG001', NULL, 10, 'The Fame is the debut studio album by Lady Gaga, released on August 19, 2008 by Interscope Records. The album is primarily a dance-pop and electropop album, with influences of disco, glam rock, and synth-pop music. It features the hit singles "Just Dance", "Poker Face", and "Paparazzi".', 8.99),
('ALB005', 'GEN004', 'SNG002', NULL, 13, 'x (pronounced "multiply") is the second studio album by English singer-songwriter Ed Sheeran. It was released on 20 June 2014 by Asylum Records and Atlantic Records. The album features the hit singles "Sing", "Dont AND Thinking Out Loud', 10.99),
('ALB006', 'GEN005', 'SNG001', NULL, 11, 'Cheek to Cheek is a collaborative album by American singers Tony Bennett and Lady Gaga. It was released on September 19, 2014, by Streamline and Columbia Records. The album consists of jazz standards, with the help of a live band and a big band. It features songs such as "Anything Goes" and "I Cant Give You Anything But Love".', 11.99);

INSERT INTO song (songID, albumID, songName, duration, thumnail, songDescription) VALUES
('SONG001', 'ALB001', 'Hello', 295, NULL, 'Hello is a song by English singer-songwriter Adele, released in 2015. It was the lead single from her third studio album, 25, and became a commercial success, reaching the top of the charts in multiple countries.'),
('SONG002', 'ALB001', 'When We Were Young', 290, NULL, 'When We Were Young is a song by Adele, released in 2016. It was the second single from her third studio album, 25.'),
('SONG003', 'ALB002', 'Shape of You', 233, NULL, 'Shape of You is a song by English singer-songwriter Ed Sheeran, released in 2017. It was the lead single from his third studio album, ÷ (pronounced "divide"), and became a commercial success, topping charts in multiple countries.'),
('SONG004', 'ALB002', 'Castle on the Hill', 261, NULL, 'Castle on the Hill is a song by Ed Sheeran, released in 2017. It was the second single from his third studio album, ÷ (pronounced "divide").'),
('SONG005', 'ALB003', 'Crazy in Love', 213, NULL, 'Crazy in Love is a song by American singer Beyoncé, featuring rapper Jay-Z. It was released in 2003 as the lead single from Beyoncés debut solo album, Dangerously in Love.'),
('SONG006', 'ALB003', 'Single Ladies (Put a Ring on It)', 214, NULL, 'Single Ladies (Put a Ring on It) is a song by Beyoncé, released in 2008 as the lead single from her third studio album, I Am... Sasha Fierce. It became a commercial success, reaching the top of the charts in multiple countries.'),
('SONG007', 'ALB004', 'Love Story', 237, NULL, 'Love Story is a song by American singer-songwriter Taylor Swift, released in 2008 as the lead single from her second studio album, Fearless. It became a commercial success, reaching the top of the charts in multiple countries.'),
('SONG008', 'ALB004', 'You Belong with Me', 231, NULL, 'You Belong with Me is a song by Taylor Swift, released in 2009 as the third single from her second studio album, Fearless. It became a commercial success, reaching the top of the charts in multiple countries.'),
('SONG009', 'ALB005', 'Just the Way You Are', 221, NULL, 'Just the Way You Are is a song by American singer-songwriter Bruno Mars, released in 2010 as the lead single from his debut studio album, Doo-Wops & Hooligans. It became a commercial success, reaching the top of the charts in multiple countries.'),
('SONG010', 'ALB005', 'Uptown Funk', 270, NULL, 'Uptown Funk is a song by Mark Ronson featuring Bruno Mars, released in 2014. It was the lead single from Ronsons fourth studio album, Uptown Special, and became a commercial success, reaching the top of the charts in multiple countries.'),
('SONG011', 'ALB006', 'Bad Romance', 295, NULL, 'Bad Romance is a song by American singer Lady Gaga, released in 2009 as the lead single from her second studio album, The Fame Monster. It became a commercial success, reaching the top of the charts in multiple countries.'),
('SONG012', 'ALB006', 'Born This Way', 258, NULL, 'Born This Way is a song by Lady Gaga, released in 2011 as the lead single from her second studio album of the same name. It became a commercial success, reaching the top of the charts in multiple countries.'),
('SONG013', 'ALB006', 'Thinking Out Loud', 281, NULL, 'Thinking Out Loud is a song by Ed Sheeran, released in 2014. It was the third single from his second studio album, x (pronounced "multiply"), and became a commercial success, reaching the top of the charts in multiple countries.'),
('SONG014', 'ALB006', 'Photograph', 258, NULL, 'Photograph is a song by Ed Sheeran, released in 2015 as the fifth single from his second studio album, x (pronounced "multiply").'),
('SONG015', 'ALB005', 'Cant Stop the Feeling!', 236, NULL, 'Cant Stop the Feeling! is a song by American singer Justin Timberlake, released in 2016. It was the lead single from the soundtrack for the animated film Trolls, and became a commercial success, reaching the top of the charts in multiple countries.'),
('SONG016', 'ALB005', 'Mirrors', 289, NULL, 'Mirrors is a song by Justin Timberlake, released in 2013 as the second single from his fourth studio album, The 20/20 Experience. It became a commercial success, reaching the top of the charts in multiple countries.'),
('SONG017', 'ALB005', 'Billie Jean', 292, NULL, 'Billie Jean is a song by American singer Michael Jackson, released in 1983 as the second single from his sixth studio album, Thriller. It became a commercial success, reaching the top of the charts in multiple countries.'),
('SONG018', 'ALB001', 'Beat It', 258, NULL, 'Beat It is a song by Michael Jackson, released in 1983 as the third single from his sixth studio album, Thriller. It became a commercial success, reaching the top of the charts in multiple countries.'),
('SONG019', 'ALB001', 'Sugar', 235, NULL, 'Sugar is a song by American pop rock band Maroon 5, released in 2015 as the third single from their fifth studio album, V. It became a commercial success, reaching the top of the charts in multiple countries.'),
('SONG020', 'ALB001', 'Girls Like You', 235, NULL, 'Girls Like You is a song by Maroon 5 featuring Cardi B, released in 2018 as the third single from their sixth studio album, Red Pill Blues. It became a commercial success, reaching the top of the charts in multiple countries.');

INSERT INTO `receipt` (`receiptID`, `userID`, `total`, `dateEstablised`, `state`) VALUES
('RCPT001', 'USER003', 20.50, '2023-04-27 10:30:00', 'Paid'),
('RCPT002', 'USER001', 15.00, '2023-04-27 11:00:00', 'Unpaid'),
('RCPT003', 'USER001', 50.25, '2023-04-27 12:30:00', 'Unpaid'),
('RCPT004', 'USER002', 10.75, '2023-04-27 13:00:00', 'Paid'),
('RCPT005', 'USER003', 30.00, '2023-04-27 14:30:00', 'Unpaid');

INSERT INTO receiptLine (receiptID, albumID, quantity, price) VALUES
('RCPT001', 'ALB001', 2, 5.25),
('RCPT001', 'ALB002', 1, 9.00),
('RCPT002', 'ALB003', 1, 15.00),
('RCPT003', 'ALB004', 2, 12.50),
('RCPT004', 'ALB005', 1, 10.75),
('RCPT005', 'ALB005', 3, 10.00),
('RCPT005', 'ALB001', 2, 5.00);
