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
    
    public partial class Kompanije
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Kompanije()
        {
            this.KompanijeKategorije = new HashSet<KompanijeKategorije>();
            this.Ponude = new HashSet<Ponude>();
            this.Servisi = new HashSet<Servisi>();
            this.Upiti = new HashSet<Upiti>();
        }
    
        public int KompanijaID { get; set; }
        public string Naziv { get; set; }
        public string Adresa { get; set; }
        public string Telefon { get; set; }
        public string Email { get; set; }
        public string KorisickoIme { get; set; }
        public string LozinkaSalt { get; set; }
        public string LozinkaHash { get; set; }
        public int GradID { get; set; }
    
        public virtual Gradovi Gradovi { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<KompanijeKategorije> KompanijeKategorije { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Ponude> Ponude { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Servisi> Servisi { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Upiti> Upiti { get; set; }
    }
}
