//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace RKS_IB150071_WebServisi.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class Servisi
    {
        public int ServisID { get; set; }
        public System.DateTime DatumPrihvatanja { get; set; }
        public Nullable<System.DateTime> DatumPocetka { get; set; }
        public Nullable<System.DateTime> DatumZavršetka { get; set; }
        public Nullable<decimal> Cijena { get; set; }
        public Nullable<int> TrajanjeDani { get; set; }
        public int KompanijaID { get; set; }
        public int PonudaID { get; set; }
    
        public virtual Kompanije Kompanije { get; set; }
        public virtual Ponude Ponude { get; set; }
    }
}
