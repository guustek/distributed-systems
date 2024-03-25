using CSharpClient.PS4ServiceImpl;

var serviceClient = new PS4ServiceClient();

const string projectName = "CSharpClient";

Console.WriteLine("GetHello:");
GetHelloResponse? helloResponse = await serviceClient.GetHelloAsync(projectName);
Console.WriteLine(helloResponse.Result);
Console.WriteLine();

Console.WriteLine("GetProducts:");
GetProductsResponse? productsResponse = await serviceClient.GetProductsAsync();
Product[] products = productsResponse.Products;
products.ToList().ForEach(Console.WriteLine);
Console.WriteLine();


var product = new Product
{
    Name = projectName, 
    Description = projectName + "Description", 
    Price = 8
};
Console.WriteLine("AddProduct: " + product);
await serviceClient.AddProductAsync(product);
Console.WriteLine();

Console.WriteLine("GetProductByName: " + projectName);
GetProductByNameResponse productResponse = await serviceClient.GetProductByNameAsync(projectName);
Console.WriteLine(productResponse.Product);
Console.WriteLine();

Console.WriteLine("GetProducts:");
productsResponse = await serviceClient.GetProductsAsync();
products = productsResponse.Products;
products.ToList().ForEach(Console.WriteLine);
Console.WriteLine();