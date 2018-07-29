using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RKS_IB150071_WebServisi.Model2
{
    public class KompanijePregledVM
    {
        public class Row
        {
            public int KompanijaID { get; set; }
            public string Naziv { get; set; }
            public string Adresa { get; set; }
            public string Telefon { get; set; }
            public string Email { get; set; }
            public string KorisickoIme { get; set; }
            public string LozinkaSalt { get; set; }
            public string LozinkaHash { get; set; }
            public int GradID { get; set; }
            public string Grad { get; set; }

        }
        public List<Row> rows;

    }
}