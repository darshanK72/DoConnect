using System.Security.Claims;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using PostService.Data;
using PostService.DTOs;
using PostService.Models;

namespace PostService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PostController : ControllerBase
    {
        private readonly PostDbContext _context;
        private readonly IWebHostEnvironment _environment;

        public PostController(PostDbContext _context, IWebHostEnvironment _environment)
        {
            this._context = _context;
            this._environment = _environment;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Post>>> GetPosts()
        {
            return await _context.Posts.OrderByDescending(p => p.CreatedAt).ToListAsync();
        }

        [Authorize]
        [HttpPost]
        public async Task<ActionResult<Post>> CreatePost([FromForm] PostCreateDto postDto)
        {
            var userId = User.FindFirstValue(ClaimTypes.NameIdentifier);
            var username = User.FindFirstValue(ClaimTypes.Name);

            var post = new Post
            {
                Title = postDto.Title,
                Content = postDto.Content,
                UserId = userId ?? "anonymous",
                Username = username ?? "anonymous",
                CreatedAt = DateTime.UtcNow
            };

            if (postDto.Image != null)
            {
                var uploadsFolder = Path.Combine(_environment.ContentRootPath, "wwwroot", "uploads");
                if (!Directory.Exists(uploadsFolder)) Directory.CreateDirectory(uploadsFolder);

                var fileName = Guid.NewGuid().ToString() + Path.GetExtension(postDto.Image.FileName);
                var filePath = Path.Combine(uploadsFolder, fileName);

                using (var stream = new FileStream(filePath, FileMode.Create))
                {
                    await postDto.Image.CopyToAsync(stream);
                }

                post.ImageUrl = $"/uploads/{fileName}";
            }

            _context.Posts.Add(post);
            await _context.SaveChangesAsync();

            return CreatedAtAction(nameof(GetPosts), new { id = post.Id }, post);
        }
    }
}
