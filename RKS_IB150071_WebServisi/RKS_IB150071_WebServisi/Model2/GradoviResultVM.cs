using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RKS_IB150071_WebServisi.Model2
{
    public class GradoviResultVM
    {

        public class Row
        {
            public int GradID { get; set; }
            public string Naziv { get; set; }
        }

        public List<Row> rows;
    }
}