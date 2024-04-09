import mysql.connector

# Create a connection to the database
cnx = mysql.connector.connect(
    host='localhost',
    port=33060,
    user='user',
    password='mysql',
    database='bustickets'
)

# Create a cursor object
cursor = cnx.cursor()

# Define the SQL command
add_trip = ("INSERT INTO trip "
            "(id, origin, destination, departure_date_time, arrival_date_time, company, price, currency, available_seats) "
            "VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)")

# Define the data to insert
trip_data = (1, 'Aveiro', 'Porto', '2024-01-01 12:00:00', '2024-01-01 14:00:00', 'Rede Expressos', 15.5, 'EUR', 30)
trip_data2 = (2, 'Aveiro', 'Lisboa', '2024-01-01 12:00:00', '2024-01-01 16:00:00', 'Flixbus', 20.0, 'EUR', 30)
trip_data3 = (3, 'Porto', 'Lisboa', '2024-01-02 09:00:00', '2024-01-02 13:00:00', 'Rede Expressos', 18.0, 'EUR', 30)
trip_data4 = (4, 'Lisboa', 'Faro', '2024-01-02 14:00:00', '2024-01-02 17:00:00', 'Flixbus', 15.0, 'EUR', 30)
trip_data5 = (5, 'Faro', 'Lisboa', '2024-01-03 08:00:00', '2024-01-03 11:00:00', 'Transdev', 15.0, 'EUR', 30)
trip_data6 = (6, 'Lisboa', 'Porto', '2024-01-03 12:00:00', '2024-01-03 16:00:00', 'Flixbus', 18.0, 'EUR', 30)
trip_data7 = (7, 'Porto', 'Aveiro', '2024-01-04 09:00:00', '2024-01-04 10:00:00', 'Rede Expressos', 10.0, 'EUR', 30)
trip_data8 = (8, 'Aveiro', 'Lisboa', '2024-01-04 11:00:00', '2024-01-04 15:00:00', 'Transdev', 20.0, 'EUR', 30)
trip_data9 = (9, 'Lisboa', 'Faro', '2024-01-05 09:00:00', '2024-01-05 12:00:00', 'Rede Expressos', 15.0, 'EUR', 30)
trip_data10 = (10, 'Faro', 'Lisboa', '2024-01-05 13:00:00', '2024-01-05 16:00:00', 'Flixbus', 15.0, 'EUR', 30)
trip_data11 = (11, 'Lisboa', 'Porto', '2024-01-06 09:00:00', '2024-01-06 13:00:00', 'Rede Expressos', 18.0, 'EUR', 30)
trip_data12 = (12, 'Porto', 'Aveiro', '2024-01-06 14:00:00', '2024-01-06 15:00:00', 'Flixbus', 10.0, 'EUR', 30)
trip_data13 = (13, 'Aveiro', 'Faro', '2024-01-07 12:00:00', '2024-01-07 14:00:00', 'Transdev', 15.5, 'EUR', 30)
trip_data14 = (14, 'Porto', 'Faro', '2024-01-07 12:00:00', '2024-01-07 14:00:00', 'Rede Expressos', 15.5, 'EUR', 30)
trip_data15 = (15, 'Lisboa', 'Aveiro', '2024-01-07 12:00:00', '2024-01-07 14:00:00', 'Flixbus', 15.5, 'EUR', 30)
# Execute the SQL command
cursor.execute(add_trip, trip_data) 
cursor.execute(add_trip, trip_data2)
cursor.execute(add_trip, trip_data3)
cursor.execute(add_trip, trip_data4)
cursor.execute(add_trip, trip_data5)
cursor.execute(add_trip, trip_data6)
cursor.execute(add_trip, trip_data7)
cursor.execute(add_trip, trip_data8)
cursor.execute(add_trip, trip_data9)
cursor.execute(add_trip, trip_data10)
cursor.execute(add_trip, trip_data11)
cursor.execute(add_trip, trip_data12)
cursor.execute(add_trip, trip_data13)
cursor.execute(add_trip, trip_data14)
cursor.execute(add_trip, trip_data15)

# Commit the changes
cnx.commit()

# Close the cursor and connection
cursor.close()
cnx.close()