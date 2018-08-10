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
    public class UpitiController : ApiController
    {
        private RKS_150071Entities db = new RKS_150071Entities();

        // GET: api/Upiti
        [HttpGet]
        [Route("api/upiti/getUpitiByKlijentID/{id}")]
        public IHttpActionResult GetUpiti(string id)
        {

            int IDint = Convert.ToInt32(id);

            var model = new UpitiResultVM
            {
                rows = db.Upiti.Where(k => k.KlijentID == IDint).Select(s => new UpitiResultVM.Row
                {

                    UpitID = s.UpitID,
                    Naslov = s.Naslov,
                    OpisKvara = s.OpisKvara,
                    MarkaUredjaja = s.MarkaUredjaja,
                    KlijentID = s.KlijentID


                }).ToList()
            };
            return Ok(model);


        }

        // GET: api/Upiti/5
        [ResponseType(typeof(Upiti))]
        public IHttpActionResult GetUpiti(int id)
        {
            Upiti upiti = db.Upiti.Find(id);
            if (upiti == null)
            {
                return NotFound();
            }

            return Ok(upiti);
        }


        [HttpGet]
        [Route("api/Upiti/GetUpitByID/{id}")]
        public IHttpActionResult GetUpitByID(int id)
        {
            int ID = Convert.ToInt32(id);

            Upiti u = db.Upiti.Include(p=>p.Kompanije).Where(x => x.UpitID == ID).FirstOrDefault();

            UpitPostVM up = new UpitPostVM();

            up.Kompanija = u.Kompanije.Naziv;
            up.MarkaUredjaja = u.MarkaUredjaja;
            up.OpisKvara = u.OpisKvara;
            up.Naslov = u.Naslov;
            up.Datum = u.Datum;

            if (u.Slika != null)
            {
                up.EncodedImage = Convert.ToBase64String(u.Slika);
            }
            else
            {
                up.EncodedImage = "x";
            }


            return Ok(up);



        }



        // PUT: api/Upiti/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutUpiti(int id, Upiti upiti)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != upiti.UpitID)
            {
                return BadRequest();
            }

            db.Entry(upiti).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!UpitiExists(id))
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

        // POST: api/Upiti
        [ResponseType(typeof(Upiti))]
        public IHttpActionResult PostUpiti([FromBody]UpitPostVM upiti)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            Upiti u = new Upiti();

            u.MarkaUredjaja = upiti.MarkaUredjaja;
            u.OpisKvara = upiti.OpisKvara;
            u.ZeljeniDatumPrijemaDo = upiti.ZeljeniDatumPrijemaDo;
            u.ZeljeniDatumPrijemaOd = upiti.ZeljeniDatumPrijemaOd;
            u.KlijentID = upiti.KlijentID;
            u.Naslov = upiti.Naslov;
            u.Datum = upiti.Datum;
            u.Odgovoreno = upiti.Odgovoreno;
            u.KompanijaID = upiti.KompanijaID;

            if (upiti.EncodedImage != null && upiti.EncodedImage != "0")
            {
                byte[] imageBytes = Convert.FromBase64String(upiti.EncodedImage);
                u.Slika = imageBytes;
            }
            else
            {
                u.Slika = null;
            }


            db.Upiti.Add(u);
            db.SaveChanges();


            return Ok();

            // return CreatedAtRoute("DefaultApi", new { id = upiti.UpitID }, upiti);
        }

        // DELETE: api/Upiti/5
        [ResponseType(typeof(Upiti))]
        public IHttpActionResult DeleteUpiti(int id)
        {
            Upiti upiti = db.Upiti.Find(id);
            if (upiti == null)
            {
                return NotFound();
            }

            db.Upiti.Remove(upiti);
            db.SaveChanges();

            return Ok(upiti);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool UpitiExists(int id)
        {
            return db.Upiti.Count(e => e.UpitID == id) > 0;
        }
    }
}