using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RKS_IB150071_WebServisi.Model2
{
    public class UpitiResultVM
    {
        public class Row
        {
            public int UpitID { get; set; }
            public string MarkaUredjaja { get; set; }
            public string OpisKvara { get; set; }
            public int KlijentID { get; set; }
            public string Naslov { get; set; }

        }
        public List<Row> rows;


    }
}