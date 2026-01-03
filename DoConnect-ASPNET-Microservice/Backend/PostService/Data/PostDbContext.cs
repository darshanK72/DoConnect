using Microsoft.EntityFrameworkCore;
using PostService.Models;

namespace PostService.Data
{
    public class PostDbContext : DbContext
    {
        public PostDbContext(DbContextOptions<PostDbContext> options) : base(options)
        {
        }

        public DbSet<Post> Posts { get; set; }
    }
}
