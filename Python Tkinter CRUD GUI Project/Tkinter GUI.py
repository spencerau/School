from os import stat
import sqlite3
from tkinter import *
import traceback
from PIL import Image, ImageTk

root = Tk()
root.title("Python Tkinter SQLite Project")

# create database or connect to one
connect = sqlite3.connect("database_project.db")
# create cursor
cur = connect.cursor()

options_1 = [
    "Male",
    "Female",
    "Other"
]

options_2 = [
    "Student",
    "Employee",
    "Faculty",
    "None"
]

clicked_1 = StringVar()
clicked_2 = StringVar()

# creates a TextArea Widget that documents
def text_area():
    # create database or connect to one
    connect = sqlite3.connect("database_project.db")
    # create cursor
    cur = connect.cursor()

    # create TextArea Widget
    top = Toplevel()
    text_area = Text(top, height=2, width=30)
    text_area.pack()
    text_area.insert(END, "TextArea Widget")

    # commit changes
    connect.commit()
    # close connection
    connect.close()

# create a function that opens a new window and displays the records on the database
def new_display():
    # create database or connect to one
    connect = sqlite3.connect("database_project.db")
    # create cursor
    cur = connect.cursor()

    # display the records in the database
    top = Toplevel()
    top.geometry("750x400")
    # ListBox Widget Stuff
    list_box = Listbox(top, bg='#808080', fg="red", highlightcolor="#FFFF00", height=15, width=45)
    # query the database
    cur.execute("SELECT *, oid FROM people")
    records = cur.fetchall()

    # loop through results
    index = 1
    print_records = ""
    for record in records:
        #print_records += str(record) + "\n"
        list_box.insert(index, record[0] + " " + record[1] + " - " + record[2] + " - " + record[3] + ", " + record[4] + " - " + record[5])
    list_box.pack()
    # commit changes
    connect.commit()
    # close connection
    connect.close()

# create edit and update function to update record
def update():
    # create database or connect to one
    connect = sqlite3.connect("database_project.db")
    # create cursor
    cur = connect.cursor()

    record_id = delete_box.get()
    cur.execute("""UPDATE people SET
        first_name = :first,
        last_name = :last,
        gender = :gender,
        city = :city,
        state = :state,
        status = :status

        WHERE oid = :oid""",
        {
        'first': f_name_editor.get(),
        'last': l_name_editor.get(),
        'gender': gender_editor.get(),
        'city': city_editor.get(),
        'state': state_editor.get(),
        'status': clicked_2.get(),
        'oid': record_id
        })

    # commit changes
    connect.commit()
    # close connection
    connect.close()
    # close the window
    editor.destroy()

def edit():
    global editor
    editor = Tk()
    editor.title("Update a Record")

    # create database or connect to one
    connect = sqlite3.connect("database_project.db")
    # create cursor
    cur = connect.cursor()

    record_id = delete_box.get()
    # query the database
    cur.execute("SELECT * FROM people WHERE oid = " + record_id)
    records = cur.fetchall()

    # create global variables for text box names
    global f_name_editor
    global l_name_editor
    global gender_editor
    global city_editor
    global state_editor
    global status_editor

    # create text boxes
    f_name_editor = Entry(editor, width=30)
    f_name_editor.grid(row=0, column=1, padx=20, pady=(10, 0))
    l_name_editor = Entry(editor, width=30)
    l_name_editor.grid(row=1, column=1, padx=20)
    gender_editor = Entry(editor, width=30)
    #gender_editor = OptionMenu(editor, clicked_1, *options_1)
    gender_editor.grid(row=2, column=1, padx=20)
    city_editor = Entry(editor, width=30)
    city_editor.grid(row=4, column=1, padx=20)
    state_editor = Entry(editor, width=30)
    state_editor.grid(row=5, column=1, padx=20)
    status_editor = OptionMenu(editor, clicked_2, *options_2)
    status_editor.grid(row=6, column=1, padx=20)

    # create text box labels
    f_name_label_editor = Label(editor, text="First Name")
    f_name_label_editor.grid(row=0, column=0, pady=(10, 0))
    l_name_label_editor = Label(editor, text="Last Name")
    l_name_label_editor.grid(row=1, column=0)
    gender_label_editor = Label(editor, text="Gender")
    gender_label_editor.grid(row=2, column=0)
    city_label_editor = Label(editor, text="City")
    city_label_editor.grid(row=4, column=0)
    state_label_editor = Label(editor, text="State")
    state_label_editor.grid(row=5, column=0)
    status_label_editor = Label(editor, text="Status")
    status_label_editor.grid(row=6, column=0)

     # fill in boxes
    for record in records:
        f_name_editor.insert(0, record[0])
        l_name_editor.insert(0, record[1])
        gender_editor.insert(0, record[2])
        city_editor.insert(0, record[3])
        state_editor.insert(0, record[4])
        #status_editor.set(record[5])    

    # create a save button to save edited updated record
    update_button = Button(editor, text="Save Record", command=update)
    update_button.grid(row=8, column=0, columnspan=2, padx=10, pady=10, ipadx=145)

# create function to delete a record
def delete():
     # create database or connect to one
    connect = sqlite3.connect("database_project.db")
    # create cursor
    cur = connect.cursor()

    # delete a record
    cur.execute("DELETE from people WHERE oid= " + delete_box.get())

    # commit changes
    connect.commit()
    # close connection
    connect.close()

# create submit function for database
def submit():
    # create database or connect to one
    connect = sqlite3.connect("database_project.db")
    # create cursor
    cur = connect.cursor()
    # insert into table
    cur.execute("INSERT INTO people VALUES (:f_name, :l_name, :gender, :city, :state, :status)",
    {
        'f_name': f_name.get(),
        'l_name': l_name.get(),
        'gender': gender.get(),
        'city': city.get(),
        'state': state.get(),
        'status': clicked_2.get()
    })
    # commit changes
    connect.commit()
    # close connection
    connect.close()
    # clear the text boxes
    f_name.delete(0, END)
    l_name.delete(0, END)
    gender.delete(0, END)
    city.delete(0, END)
    state.delete(0, END)

# create query function
def query():
    # create database or connect to one
    connect = sqlite3.connect("database_project.db")
    # create cursor
    cur = connect.cursor()

    # query the database
    cur.execute("SELECT *, oid FROM people")
    records = cur.fetchall()
    print(records)

    # loop through results
    print_records = ''
    for record in records:
        print_records += str(record) + "\n"

    query_label = Label(root, text=print_records)
    query_label.grid(row=17, column=0, columnspan=2)

    # commit changes
    connect.commit()
    # close connection
    connect.close()

# create text boxes
f_name = Entry(root, width=30)
f_name.grid(row=0, column=1, padx=20, pady=(10, 0))
l_name = Entry(root, width=30)
l_name.grid(row=1, column=1, padx=20)
gender = Entry(root, width=30)
#gender = OptionMenu(root, clicked_1, *options_1)
gender.grid(row=2, column=1, padx=20)
city = Entry(root, width=30)
city.grid(row=4, column=1, padx=20)
state = Entry(root, width=30)
state.grid(row=5, column=1, padx=20)
status = OptionMenu(root, clicked_2, *options_2)
status.grid(row=6, column=1, padx=20)
delete_box = Entry(root, width=20)
delete_box.grid(row=12, column=1, pady=5)

# create text box labels
f_name_label = Label(root, text="First Name")
f_name_label.grid(row=0, column=0, pady=(10, 0))
l_name_label = Label(root, text="Last Name")
l_name_label.grid(row=1, column=0)
gender_label = Label(root, text="Gender")
gender_label.grid(row=2, column=0)
city_label = Label(root, text="City")
city_label.grid(row=4, column=0)
state_label = Label(root, text="State")
state_label.grid(row=5, column=0)
status_label = Label(root, text="Status")
status_label.grid(row=6, column=0)
delete_box_label = Label(root, text="Select by ID Number")
delete_box_label.grid(row=12, column=0, padx=(40,0))

# create submit button
submit_button = Button(root, text="Register User", command=submit)
submit_button.grid(row=8, column=0, columnspan=2, padx=10, pady=10, ipadx=140)

# create a query button
query_button = Button(root, text="Show Records at Bottom", command=query)
query_button.grid(row=9, column=0, columnspan=2, padx=10, pady=10, ipadx=110)

# create a delete button
delete_button = Button(root, text="Delete Record via ID", command=delete)
delete_button.grid(row=13, column=0, columnspan=2, padx=10, pady=10, ipadx=125)

# create an update button
update_button = Button(root, text="Update Record via ID", command=edit)
update_button.grid(row=14, column=0, columnspan=2, padx=10, pady=10, ipadx=123)

# create a button to open a new window and show records
records_button = Button(root, text="ListBox - Show Records", command=new_display)
records_button.grid(row=15, column=0, columnspan=2, padx=10, pady=10, ipadx=115)

# create button for opening a new 
text_area_button = Button(root, text="TextArea Button", command=text_area)
text_area_button.grid(row=16, column=0, columnspan=2, padx=10, pady=10, ipadx=135)

# commit changes
connect.commit()
# close connection
connect.close()

root.mainloop()
