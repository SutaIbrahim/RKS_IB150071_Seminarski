using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using RKS_IB150071_WebServisi.Model2;
using RKS_IB150071_WebServisi.Models;

namespace RKS_IB150071_WebServisi.Controllers
{
    public class KompanijeController : ApiController
    {
        private RKS_150071Entities db = new RKS_150071Entities();

        // GET: api/Kompanije

             [HttpGet]
            [Route("api/Kompanije/GetKompanije/{naziv?}")]
        public IHttpActionResult GetKompanije(string naziv="")
        {

            //List<Kompanije> k= db.Kompanije.ToList();

            //List<KompanijePregledVM> model = new List<KompanijePregledVM>();

            //foreach (var x in k)
            //{
            //    KompanijePregledVM n = new KompanijePregledVM();
            //    n.KompanijaID = x.KompanijaID;
            //    n.Naziv = x.Naziv;
            //    n.Adresa = x.Adresa;
            //    n.Telefon = x.Telefon;
            //    n.Email = x.Email;
            //    n.KorisickoIme = x.KorisickoIme;
            //    n.LozinkaSalt = x.LozinkaSalt;
            //    n.LozinkaHash = x.LozinkaHash;
            //    n.GradID = x.GradID;

            //    model.Add(n);
            //}

            //return model;


            if (naziv != "---" && naziv != "")
            {

                var model = new KompanijePregledVM
                {
                    rows = db.Kompanije.OrderBy(x => x.Naziv).Include(g => g.Gradovi).Where(n => n.Gradovi.Naziv == naziv).Select(s => new KompanijePregledVM.Row
                    {
                        KompanijaID = s.KompanijaID,
                        Naziv = s.Naziv,
                        Adresa = s.Adresa,
                        Telefon = s.Telefon,
                        Email = s.Email,
                        KorisickoIme = s.KorisickoIme,
                        LozinkaSalt = s.LozinkaSalt,
                        LozinkaHash = s.LozinkaHash,
                        GradID = s.GradID,
                        Grad = s.Gradovi.Naziv

                    }).ToList()
                };
                return Ok(model);

            }

            else
            {

                var model = new KompanijePregledVM
                {
                    rows = db.Kompanije.OrderBy(x => x.Naziv).Include(g => g.Gradovi).Select(s => new KompanijePregledVM.Row
                    {
                        KompanijaID = s.KompanijaID,
                        Naziv = s.Naziv,
                        Adresa = s.Adresa,
                        Telefon = s.Telefon,
                        Email = s.Email,
                        KorisickoIme = s.KorisickoIme,
                        LozinkaSalt = s.LozinkaSalt,
                        LozinkaHash = s.LozinkaHash,
                        GradID = s.GradID,
                        Grad = s.Gradovi.Naziv

                    }).ToList()
                };
                return Ok(model);

            }

        }

        //// GET: api/Kompanije/5
        //[ResponseType(typeof(Kompanije))]
        //public IHttpActionResult GetKompanije(int id)
        //{
        //    Kompanije kompanije = db.Kompanije.Find(id);
        //    if (kompanije == null)
        //    {
        //        return NotFound();
        //    }

        //    return Ok(kompanije);
        //}

        // PUT: api/Kompanije/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutKompanije(int id, Kompanije kompanije)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != kompanije.KompanijaID)
            {
                return BadRequest();
            }

            db.Entry(kompanije).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!KompanijeExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/Kompanije
        [ResponseType(typeof(Kompanije))]
        public IHttpActionResult PostKompanije(Kompanije kompanije)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Kompanije.Add(kompanije);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = kompanije.KompanijaID }, kompanije);
        }

        // DELETE: api/Kompanije/5
        [ResponseType(typeof(Kompanije))]
        public IHttpActionResult DeleteKompanije(int id)
        {
            Kompanije kompanije = db.Kompanije.Find(id);
            if (kompanije == null)
            {
                return NotFound();
            }

            db.Kompanije.Remove(kompanije);
            db.SaveChanges();

            return Ok(kompanije);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool KompanijeExists(int id)
        {
            return db.Kompanije.Count(e => e.KompanijaID == id) > 0;
        }
    }
}