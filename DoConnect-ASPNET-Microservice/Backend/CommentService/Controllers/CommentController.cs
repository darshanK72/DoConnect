using System.Security.Claims;
using CommentService.Data;
using CommentService.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace CommentService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CommentController : ControllerBase
    {
        private readonly CommentDbContext _context;

        public CommentController(CommentDbContext _context)
        {
            this._context = _context;
        }

        [HttpGet("post/{postId}")]
        public async Task<ActionResult<IEnumerable<Comment>>> GetCommentsByPost(int postId)
        {
            return await _context.Comments.Where(c => c.PostId == postId).OrderByDescending(c => c.CreatedAt).ToListAsync();
        }

        [Authorize]
        [HttpPost]
        public async Task<ActionResult<Comment>> CreateComment([FromBody] Comment comment)
        {
            comment.UserId = User.FindFirstValue(ClaimTypes.NameIdentifier) ?? "anonymous";
            comment.Username = User.FindFirstValue(ClaimTypes.Name) ?? "anonymous";
            comment.CreatedAt = DateTime.UtcNow;

            _context.Comments.Add(comment);
            await _context.SaveChangesAsync();

            return Ok(comment);
        }
    }
}
