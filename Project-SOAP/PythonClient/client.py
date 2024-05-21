import os
import tkinter as tk
from io import BytesIO
from tkinter import messagebox
from tkinter import ttk

from PIL import Image, ImageTk
from pymtom_xop import MtomTransport
from requests import Session
from tkcalendar import DateEntry
from zeep import Client

session = Session()
session.verify = False

client = Client('https://localhost:8443/CarRentalService?wsdl', transport=MtomTransport(session=session))

service = client.service

# Example list of cars
cars = []


def search():
    start_date = start_date_entry.get_date()
    end_date = end_date_entry.get_date()
    global cars
    result = service.getAvailableCars(start_date, end_date)
    cars = result

    car_listbox.delete(0, tk.END)
    for car in cars:
        car_listbox.insert(tk.END, car.model)

    car_image_label.config(image='')
    car_details_label.config(text='')


def download_confirmation():
    confirmation_text = confirmation_entry.get()
    pdf_data = service.generatePDFReservationConfirmation(confirmation_text)
    file_name = confirmation_text + "_reservation.pdf"
    with open(file_name, "wb") as pdf_file:
        pdf_file.write(pdf_data)

    os.startfile(file_name)


def display_car_details(event):
    selected_index = car_listbox.curselection()[0]
    selected_car = cars[selected_index]

    image = Image.open(BytesIO(selected_car.image))
    image = image.resize((150, 100))
    car_image = ImageTk.PhotoImage(image)

    car_image_label.config(image=car_image)
    car_image_label.image = car_image
    car_details_label.config(text=f"""
        Model: {selected_car.model}
        Type: {selected_car.carType}
        Transmission: {selected_car.transmissionType}   
    """)


def reserve_car():
    selected_index = car_listbox.curselection()[0]
    selected_car = cars[selected_index]

    start_date = start_date_entry.get_date()
    end_date = end_date_entry.get_date()
    reservation_id = service.reserveCar(selected_car.id, start_date, end_date)
    messagebox.showinfo("Car reservation",
                        f"""
                        Reservation successful
                        Reservation id: {reservation_id}
                        """)


def cancel_reservation():
    confirmation_text = confirmation_entry.get()
    service.cancelReservation(confirmation_text)
    messagebox.showinfo("Reservation cancel",
                        f"""
                        Reservation cancelled successfully
                        Reservation id: {confirmation_text}
                        """)


root = tk.Tk()
root.title("Car Rental Application")

main_frame = ttk.Frame(root, padding="10")
main_frame.grid(row=0, column=0, sticky=(tk.W, tk.E, tk.N, tk.S))

car_listbox = tk.Listbox(main_frame)
for car in cars:
    car_listbox.insert(tk.END, car.model)
car_listbox.grid(row=0, column=0, rowspan=4)

car_listbox.bind('<<ListboxSelect>>', display_car_details)

car_image_label = tk.Label(main_frame)
car_image_label.grid(row=0, column=1)

car_details_label = tk.Label(main_frame, text="")
car_details_label.grid(row=1, column=1)

ttk.Label(main_frame, text="Start Date:").grid(row=2, column=1, sticky=tk.W)
start_date_entry = DateEntry(main_frame, date_pattern='yyyy-mm-dd')
start_date_entry.grid(row=2, column=2)

ttk.Label(main_frame, text="End Date:").grid(row=3, column=1, sticky=tk.W)
end_date_entry = DateEntry(main_frame, date_pattern='yyyy-mm-dd')
end_date_entry.grid(row=3, column=2)

search_button = ttk.Button(main_frame, text="Search", command=search)
search_button.grid(row=4, column=0, columnspan=2)

ttk.Label(main_frame, text="Confirmation:").grid(row=5, column=0, sticky=tk.W)
confirmation_entry = tk.Entry(main_frame)
confirmation_entry.grid(row=5, column=1)

download_button = ttk.Button(main_frame, text="Download confirmation", command=download_confirmation)
download_button.grid(row=5, column=2)

reserve_button = ttk.Button(main_frame, text="Reserve Car", command=reserve_car)
reserve_button.grid(row=6, column=0, columnspan=2)

cancel_button = ttk.Button(main_frame, text="Cancel Reservation", command=cancel_reservation)
cancel_button.grid(row=6, column=2)

root.mainloop()
