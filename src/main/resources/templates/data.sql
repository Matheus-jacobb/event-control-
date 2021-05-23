INSERT INTO TB_EVENT (name, description, start_date, end_date, start_time, end_time, email, amount_free_tickets, amount_payed_tickets, price_ticket, admin_id) 
VALUES (
    'Introdução Java', 
    'Palestra introdutoria sobre tecnologia java, crescimento, mercado e inovação', 
    '2022-12-25',
    '2022-12-25',
    '09:00:00',
    '12:00:00',
    'suporte@java.com.br',
    '1',
    '0',
    '0',
    '1'
    );

    INSERT INTO TB_EVENT (name, description, start_date, end_date, start_time, end_time, email, amount_free_tickets, amount_payed_tickets, price_ticket, admin_id) 
VALUES (
    'Introdução Python', 
    'Palestra introdutoria sobre tecnologia python, crescimento, mercado e inovação', 
    '2022-12-26',
    '2022-12-26',
    '09:00:00',
    '12:00:00',
    'suporte@python.com.br',
    '2',
    '1',
    '10.00',
    '2'
    );

    INSERT INTO TB_EVENT (name, description, start_date, end_date, start_time, end_time, email, amount_free_tickets, amount_payed_tickets, price_ticket, admin_id) 
VALUES (
    'Introdução C#', 
    'Palestra introdutoria sobre tecnologia C#, crescimento, mercado e inovação', 
    '2022-12-27',
    '2022-12-27',
    '09:00:00',
    '12:00:00',
    'suporte@csharp.com.br',
    '0',
    '2',
    '12.00',
    '3'
    );

        INSERT INTO TB_EVENT (name, description, start_date, end_date, start_time, end_time, email, amount_free_tickets, amount_payed_tickets, price_ticket, admin_id) 
VALUES (
    'Introdução POWER BI', 
    'Palestra introdutoria sobre tecnologia PowerBI, crescimento, mercado e inovação', 
    '2023-01-05',
    '2023-01-05',
    '09:00:00',
    '12:00:00',
    'suporte@powerbi.com.br',
    '10',
    '10',
    '20.00',
    '4'
    );

    INSERT INTO TB_EVENT (name, description, start_date, end_date, start_time, end_time, email, amount_free_tickets, amount_payed_tickets, price_ticket, admin_id) 
VALUES (
    'Introdução C', 
    'Palestra introdutoria sobre tecnologia Linguagem C, crescimento, mercado e inovação', 
    '2023-01-06',
    '2023-01-06',
    '09:00:00',
    '12:00:00',
    'suporte@linguagemc.com.br',
    '10',
    '10',
    '10.00',
    '5'
    );

    INSERT INTO TB_BASE_USER (name, email) 
VALUES (
    'Robson',
    'robson@gmail.com'
    );

    INSERT INTO TB_BASE_USER (name, email) 
VALUES (
    'Rosana',
    'rosana@gmail.com'
    );

    INSERT INTO TB_BASE_USER (name, email) 
VALUES (
    'Janaína',
    'janaina@gmail.com'
    );

    INSERT INTO TB_BASE_USER (name, email) 
VALUES (
    'Lucas',
    'lucas@gmail.com'
    );

    INSERT INTO TB_BASE_USER (name, email) 
VALUES (
    'Bruno',
    'bruno@gmail.com'
    );

    INSERT INTO TB_BASE_USER (name, email) 
VALUES (
    'Carla',
    'carla@gmail.com'
    );

    INSERT INTO TB_ADMIN (phone_number, base_user_id) 
VALUES (
    '11925636550',
    '1'
    );

    INSERT INTO TB_ADMIN (phone_number, base_user_id) 
VALUES (
    '11985458570',
    '2'
    );

    INSERT INTO TB_ADMIN (phone_number, base_user_id) 
VALUES (
    '11925968811',
    '3'
    );

    INSERT INTO TB_ADMIN (balance, base_user_id) 
VALUES (
    '11912345678',
    '4'
    );

    INSERT INTO TB_ADMIN (balance, base_user_id) 
VALUES (
    '11987456123',
    '5'
    );

    INSERT INTO TB_ADMIN (balance, base_user_id) 
VALUES (
    '11978456123',
    '6'
    );