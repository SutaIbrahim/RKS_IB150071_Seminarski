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
                rows = db.Upiti.Where(k=>k.KlijentID==IDint).Select(s => new UpitiResultVM.Row
                {

                    UpitID=s.UpitID,
                    Naslov=s.Naslov,
                    OpisKvara=s.OpisKvara,
                    Slika=s.Slika,
                    MarkaUredjaja=s.MarkaUredjaja,
                    KlijentID=s.KlijentID


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
        public IHttpActionResult PostUpiti([FromBody]Upiti upiti)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Upiti.Add(upiti);
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