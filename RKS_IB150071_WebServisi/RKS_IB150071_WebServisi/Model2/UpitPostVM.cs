using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RKS_IB150071_WebServisi.Model2
{
    public class UpitPostVM
    {

        public int UpitID { get; set; }
        public string MarkaUredjaja { get; set; }
        public string OpisKvara { get; set; }
        // public byte[] Slika { get; set; }
        public string EncodedImage { get; set; }

        public Nullable<System.DateTime> ZeljeniDatumPrijemaOd { get; set; }
        public Nullable<System.DateTime> ZeljeniDatumPrijemaDo { get; set; }
        public int KlijentID { get; set; }
        public string Naslov { get; set; }
        public Nullable<System.DateTime> Datum { get; set; }

        public int KompanijaID { get; set; }
        public bool Odgovoreno { get; set; }

        public string Kompanija { get; set; }

    }
}