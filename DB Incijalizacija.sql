create database [RKS_150071]
go
use [RKS_150071]

go


create table Gradovi(
GradID int not null identity(1,1) PRIMARY KEY,
Naziv nvarchar(50) not null

)

create table Klijenti(
KlijentID int not null identity(1,1) PRIMARY KEY,
Ime nvarchar(50) not null,
Prezime nvarchar(50) not null,
Adresa nvarchar(100) null,
Telefon nvarchar(50) not null UNIQUE,
Email nvarchar(100) not null UNIQUE,
KorisickoIme nvarchar(50) not null UNIQUE,
LozinkaSalt nvarchar(max),
LozinkaHash nvarchar(max),
GradID int FOREIGN KEY(GradID) References Gradovi(GradID)

)


create table Kompanije(
KompanijaID int not null identity(1,1) primary key,
Naziv nvarchar(50) not null,
Adresa nvarchar(100) not null,
Telefon nvarchar(50) not null,
Email nvarchar(100) not null UNIQUE,
KorisickoIme nvarchar(50) not null UNIQUE,
LozinkaSalt nvarchar(max),
LozinkaHash nvarchar(max),
GradID int not null FOREIGN KEY(GradID) References Gradovi(GradID)


)




create table Upiti(
UpitID int not null identity(1,1) PRIMARY KEY,
MarkaUredjaja nvarchar(50) null,
ModelUredjaja nvarchar(50) null,
OpisKvara nvarchar(max) not null,
Slika varbinary(max) null, 
ZeljeniDatumPrijemaOd datetime null,
ZeljeniDatumPrijemaDo datetime null,

KlijentID int not null FOREIGN KEY(KlijentID) References Klijenti(KlijentID),
)


create table KompanijeUpiti(
KompanijaUpitID int not null identity(1,1) primary key,
KompanijaID int not null foreign key(KompanijaID) references Kompanije(KompanijaID),
UpitID int not null foreign key(UpitID) references Upiti(UpitID),
Odgovoreno bit not null

)



create table Kategorije(
KategorijaID int not null IDENTITY (1,1) PRIMARY KEY,
Naziv nvarchar(50) not null UNIQUE
)

create table KompanijeKategorije(
KompanijaKategorijaID int not null identity(1,1) primary key,
KategorijaID int not null foreign key(KategorijaID) references Kategorije(KategorijaID),
KompanijaID int not null foreign key(KompanijaID) references Kompanije(KompanijaID)
)


Create table Ponude(
PonudaID int not null identity(1,1) PRimary key,
DatumKreiranja datetime not null,
Odgovor nvarchar(max) not null,
Cijena nvarchar(50) not null,
DatumNajranijegMogucegPrijema datetime not null,
TrajanjeDani decimal(18,2) not null,
TrajanjeSati decimal(18,2) not null,
Prihvacena bit not null,

KompanijaID int not null foreign key(KompanijaID) references Kompanije(KompanijaID),
KlijentID int not null foreign key(KlijentID) references Klijenti(KlijentID),
UpitID int not null foreign key(UpitID) references Upiti(UpitID)
)


Create table Servisi(
ServisID int not null identity(1,1) Primary key,
DatumPrihvatanja datetime not null,
DatumPocetka datetime null,
DatumZavršetka datetime null,
Cijena decimal null,
TrajanjeDani int null,

KompanijaID int not null foreign key (KompanijaID) references Kompanije(KompanijaID),
PonudaID int not null foreign key (PonudaID) references Ponude(PonudaID)
)


