from zeep import Client

client = Client('http://pawel:8080/PS4-soap/PS4ServiceImpl?wsdl')

response = client.service.GetHello("Python")
print(response)

response = client.service.GetProducts()
print(response)
