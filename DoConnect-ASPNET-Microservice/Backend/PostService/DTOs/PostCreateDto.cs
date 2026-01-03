namespace PostService.DTOs
{
    public class PostCreateDto
    {
        public string Title { get; set; } = string.Empty;
        public string Content { get; set; } = string.Empty;
        public IFormFile? Image { get; set; }
    }
}
