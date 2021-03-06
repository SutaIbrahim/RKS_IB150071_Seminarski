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
    
    public partial class Ponude
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Ponude()
        {
            this.Servisi = new HashSet<Servisi>();
        }
    
        public int PonudaID { get; set; }
        public System.DateTime DatumKreiranja { get; set; }
        public string Odgovor { get; set; }
        public string Cijena { get; set; }
        public System.DateTime DatumNajranijegMogucegPrijema { get; set; }
        public decimal TrajanjeDani { get; set; }
        public decimal TrajanjeSati { get; set; }
        public bool Prihvacena { get; set; }
        public int KompanijaID { get; set; }
        public int KlijentID { get; set; }
        public int UpitID { get; set; }
    
        public virtual Klijenti Klijenti { get; set; }
        public virtual Kompanije Kompanije { get; set; }
        public virtual Upiti Upiti { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Servisi> Servisi { get; set; }
    }
}
