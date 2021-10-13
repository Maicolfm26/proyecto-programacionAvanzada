--Se crean las ciudades en la base de datos
insert into Ciudad values("123","Armenia");
insert into Ciudad values("124", "Pereira");
insert into Ciudad values("125", "Medellin");
insert into Ciudad values("126", "Bogota");
insert into Ciudad values("127", "Cali");

--Se crean las categorias en la base de datos como datos de prueba
insert into Categoria values(1, "Ropa");
insert into Categoria values(2, "Tenis");
insert into Categoria values(3, "Jeans");
insert into Categoria values(4, "Carros");

--Se crean algunos usuarios en la base de datos
insert into Usuario values("100765489", "carlos@gmail.com", "Carlos Florez", "123", "123");
insert into Usuario values("42785998", "maria@gmail.com", "Maria Zapata", "1130", "124");
insert into Usuario values("13838543", "antonio@gmail.com", "Antonio Lopez", "1232", "127");
insert into Usuario values("98765554", "gisela@gmail.com", "Gisela Carvajal", "0000", "125");
insert into Usuario values("3212345", "mariana@gmail.com", "Mariana Carabali", "1111", "126");

--Se insertan algunos productos en la base de datos
insert into Producto values(1, "Camiseta de tela fria", null, "2021-11-11", "Camisa", 20000, 5, "123", "100765489"  );
insert into Producto values(2, "Jean jogger", null, "2021-10-22", "Jogger", 30000, 15, "124", "42785998"  );
insert into Producto values(3, "Tenis af1 blancos", null, "2021-12-22", "AF1", 130000, 3, "127", "13838543"  );

--Se insertan algunas imagenes a la base de datos
insert into Producto_imagenes values(1, "C:\Users\tavog\imagen1.jpg" );
insert into Producto_imagenes values(2, "C:\Users\tavog\imagen2.jpg" );
insert into Producto_imagenes values(3, "C:\Users\tavog\imagen3.jpg" );

--Se insertan algunos comentarios como prueba
insert into Comentario values(1, 4, "2021-10-12", "Muy buen producto", null, 1, "98765554" );
insert into Comentario values(2, 5, "2021-10-11", "Excelente producto", null, 2, "3212345" );

