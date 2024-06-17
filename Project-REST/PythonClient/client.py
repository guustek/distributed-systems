import os
import sys
import base64
import tkinter as tk
from http import HTTPStatus

import tkcalendar as tkc
import io
import tkinter
import babel
import babel.numbers
from tkinter import ttk, messagebox

from PIL import Image, ImageTk  # Updated import
import requests
from requests.auth import HTTPBasicAuth

if len(sys.argv) < 5:
    print("Usage: <host> <useSSL> <username> <password>")
    sys.exit(1)

host = sys.argv[1]
useSSL = sys.argv[2].lower() == 'true'
username = sys.argv[3]
password = sys.argv[4]

protocol = "https" if useSSL else "http"
port = "8443" if useSSL else "8080"

base_url = f'{protocol}://{host}:{port}/car-rental'  # Adjust the URL if needed

session = requests.Session()
session.verify = False
session.auth = HTTPBasicAuth(username, password)


class CarRentalClient:
    def __init__(self, url):
        self.base_url = url

    def get_all_cars(self):
        response = requests.get(f"{self.base_url}/cars")
        if response.status_code == 200:
            return response.json()["_embedded"]["carList"]
        else:
            print(f"Request: {response.request.method} {response.url}")
            print(f"Status code: {response.status_code} {HTTPStatus(response.status_code).phrase}")
            print('Response:', response.text)

    def get_available_cars(self, from_date, to_date):
        params = {
            'from': from_date,
            'to': to_date
        }
        response = session.get(f"{self.base_url}/cars/available", params=params)
        if response.status_code == 200:
            return response.json()["_embedded"]["carList"]
        else:
            print(f"Request: {response.request.method} {response.url}")
            print(f"Status code: {response.status_code} {HTTPStatus(response.status_code).phrase}")
            print('Response:', response.text)

    def get_car_by_id(self, car_id):
        response = session.get(f"{self.base_url}/cars/{car_id}")
        if response.status_code == 200:
            return response.json()
        else:
            print(f"Request: {response.request.method} {response.url}")
            print(f"Status code: {response.status_code} {HTTPStatus(response.status_code).phrase}")
            print('Response:', response.text)

    def get_all_reservations(self):
        response = session.get(f"{self.base_url}/reservations")
        if response.status_code == 200:
            return response.json()
        else:
            print(f"Request: {response.request.method} {response.url}")
            print(f"Status code: {response.status_code} {HTTPStatus(response.status_code).phrase}")
            print('Response:', response.text)

    def get_reservation_by_id(self, reservation_id):
        response = session.get(f"{self.base_url}/reservations/{reservation_id}")
        if response.status_code == 200:
            return response.json()
        else:
            print(f"Request: {response.request.method} {response.url}")
            print(f"Status code: {response.status_code} {HTTPStatus(response.status_code).phrase}")
            print('Response:', response.text)

    def reserve_car(self, car_id, from_date, to_date):
        params = {
            'carId': car_id,
            'from': from_date.isoformat(),
            'to': to_date.isoformat()
        }
        response = session.post(f"{self.base_url}/reservations", params=params)
        if response.status_code == 200:
            return response.json()
        else:
            print(f"Request: {response.request.method} {response.url}")
            print(f"Status code: {response.status_code} {HTTPStatus(response.status_code).phrase}")
            print('Response:', response.text)

    def cancel_reservation(self, reservation_id):
        response = session.delete(f"{self.base_url}/reservations/{reservation_id}")
        if response.status_code == 200:
            print('Reservation cancelled successfully')
        else:
            print(f"Request: {response.request.method} {response.url}")
            print(f"Status code: {response.status_code} {HTTPStatus(response.status_code).phrase}")
            print('Response:', response.text)
        return response.status_code == 200

    def generate_pdf_reservation_confirmation(self, reservation_id):
        response = session.get(f"{self.base_url}/reservations/{reservation_id}/confirmation")
        if response.status_code == 200:
            return response.content
        else:
            print(f"Request: {response.request.method} {response.url}")
            print(f"Status code: {response.status_code} {HTTPStatus(response.status_code).phrase}")
            print('Response:', response.text)


client = CarRentalClient(base_url)

# Example list of cars
cars = []


def search():
    start_date = start_date_entry.get_date()
    end_date = end_date_entry.get_date()
    global cars
    result = client.get_available_cars(start_date, end_date)
    cars = result

    car_listbox.delete(0, tk.END)
    for car in cars:
        car_listbox.insert(tk.END, car["model"])

    car_image_label.config(image='')
    car_details_label.config(text='')


def download_confirmation():
    confirmation_text = confirmation_entry.get()
    pdf_data = client.generate_pdf_reservation_confirmation(confirmation_text)
    file_name = confirmation_text + "_reservation.pdf"
    with open(file_name, "wb") as pdf_file:
        pdf_file.write(pdf_data)

    os.startfile(file_name)


def display_car_details(event):
    selected_index = car_listbox.curselection()[0]
    selected_car = cars[selected_index]

    image_base64 = selected_car["image"]
    image_bytes = base64.b64decode(image_base64)

    image = Image.open(io.BytesIO(image_bytes))
    image = image.resize((150, 100))
    car_image = ImageTk.PhotoImage(image)

    car_image_label.config(image=car_image)
    car_image_label.image = car_image
    car_details_label.config(text=f"""
        Model: {selected_car["model"]}
        Type: {selected_car["carType"]}
        Transmission: {selected_car["transmissionType"]}   
    """)


def reserve_car():
    selected_index = car_listbox.curselection()[0]
    selected_car = cars[selected_index]

    start_date = start_date_entry.get_date()
    end_date = end_date_entry.get_date()
    reservation = client.reserve_car(selected_car["id"], start_date, end_date)
    reservation_id = reservation["id"]
    messagebox.showinfo("Car reservation",
                        f"""
                        Reservation successful
                        Reservation id: {reservation_id}
                        """)
    confirmation_entry.delete(0, tk.END)
    confirmation_entry.insert(0, reservation_id)
    confirmation_text = confirmation_entry.get()

    confirmation_pdf_link = reservation["_links"]["pdf-confirmation"]["href"]
    response = session.get(confirmation_pdf_link)
    if response.status_code == 200:
        file_name = confirmation_text + "_reservation.pdf"
        with open(file_name, "wb") as pdf_file:
            pdf_file.write(response.content)

        os.startfile(file_name)
    else:
        print(f"Request: {response.request.method} {response.url}")
        print(f"Status code: {response.status_code} {HTTPStatus(response.status_code).phrase}")
        print('Response:', response.text)


def cancel_reservation():
    confirmation_text = confirmation_entry.get()
    deleted = client.cancel_reservation(confirmation_text)
    if deleted:
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
    car_listbox.insert(tk.END, car["model"])
car_listbox.grid(row=0, column=0, rowspan=4)

car_listbox.bind('<<ListboxSelect>>', display_car_details)

car_image_label = tk.Label(main_frame)
car_image_label.grid(row=0, column=1)

car_details_label = tk.Label(main_frame, text="")
car_details_label.grid(row=1, column=1)

ttk.Label(main_frame, text="Start Date:").grid(row=2, column=1, sticky=tk.W)
start_date_entry = tkc.DateEntry(main_frame, date_pattern='yyyy-mm-dd')
start_date_entry.grid(row=2, column=2)

ttk.Label(main_frame, text="End Date:").grid(row=3, column=1, sticky=tk.W)
end_date_entry = tkc.DateEntry(main_frame, date_pattern='yyyy-mm-dd')
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
