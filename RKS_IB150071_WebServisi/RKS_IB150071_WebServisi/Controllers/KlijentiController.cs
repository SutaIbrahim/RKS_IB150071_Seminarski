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
using RKS_IB150071_WebServisi.Models;

namespace RKS_IB150071_WebServisi.Controllers
{
    public class KlijentiController : ApiController
    {
        private RKS_150071Entities db = new RKS_150071Entities();

        // GET: api/Klijenti
        public IQueryable<Klijenti> GetKlijenti()
        {
            return db.Klijenti;
        }

        // GET: api/Klijenti/5
        [ResponseType(typeof(Klijenti))]
        public IHttpActionResult GetKlijenti(int id)
        {
            Klijenti klijenti = db.Klijenti.Find(id);
            if (klijenti == null)
            {
                return NotFound();
            }

            return Ok(klijenti);
        }

        // PUT: api/Klijenti/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutKlijenti(Klijenti klijenti)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            

            db.Entry(klijenti).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
               
                    throw;
                
            }

            return StatusCode(HttpStatusCode.OK);
        }

        // POST: api/Klijenti
        [ResponseType(typeof(Klijenti))]
        public IHttpActionResult PostKlijenti([FromBody] Klijenti klijenti)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Klijenti.Add(klijenti);
            db.SaveChanges();

            return Ok();
            //return CreatedAtRoute("DefaultApi", new { id = klijenti.KlijentID }, klijenti);
        }

        // DELETE: api/Klijenti/5
        [ResponseType(typeof(Klijenti))]
        public IHttpActionResult DeleteKlijenti(int id)
        {
            Klijenti klijenti = db.Klijenti.Find(id);
            if (klijenti == null)
            {
                return NotFound();
            }

            db.Klijenti.Remove(klijenti);
            db.SaveChanges();

            return Ok(klijenti);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool KlijentiExists(int id)
        {
            return db.Klijenti.Count(e => e.KlijentID == id) > 0;
        }
    }
}