using RKS_IB150071_WebServisi.Model2;
using RKS_IB150071_WebServisi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace RKS_IB150071_WebServisi.Controllers
{
    public class AutentifikacijaController : ApiController
    {
        private RKS_150071Entities db = new RKS_150071Entities();



        [HttpGet]
        [Route("api/Autentifikacija/LoginCheck/{username}/{pass}")]
        public IHttpActionResult LoginCheck(string username, string pass)
        {
            Klijenti k=db.Klijenti.Where(x => x.KorisickoIme == username && x.LozinkaSalt == pass).FirstOrDefault(); // unutar lozinkaSalt je smjesten string password

            if (k != null)
            {
                AutentifikacijaResultVM a = new AutentifikacijaResultVM();
                a.KlijentID = k.KlijentID;
                a.Ime = k.Ime;
                a.Prezime = k.Prezime;
                a.KorisickoIme = k.KorisickoIme;
                a.LozinkaSalt = k.LozinkaSalt;
                a.Telefon = k.Telefon;
                a.Email = k.Email;
                return Ok(a);

            }
            return null;
        }


    }
}
