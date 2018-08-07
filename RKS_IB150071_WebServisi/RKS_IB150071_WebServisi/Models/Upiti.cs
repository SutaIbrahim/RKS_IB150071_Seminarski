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
    
    public partial class Upiti
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Upiti()
        {
            this.KompanijeUpiti = new HashSet<KompanijeUpiti>();
            this.Ponude = new HashSet<Ponude>();
        }
    
        public int UpitID { get; set; }
        public string MarkaUredjaja { get; set; }
        public string OpisKvara { get; set; }
        public byte[] Slika { get; set; }
        public Nullable<System.DateTime> ZeljeniDatumPrijemaOd { get; set; }
        public Nullable<System.DateTime> ZeljeniDatumPrijemaDo { get; set; }
        public int KlijentID { get; set; }
        public string Naslov { get; set; }
        public Nullable<System.DateTime> Datum { get; set; }
    
        public virtual Klijenti Klijenti { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<KompanijeUpiti> KompanijeUpiti { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Ponude> Ponude { get; set; }
    }
}
