namespace IdentityService.DTOs
{
    public record RegisterDto(string Email, string Password, string FirstName, string LastName, string Username);
    public record LoginDto(string Email, string Password);
    public record AuthResponseDto(bool IsSuccess, string Message, string Token = "");
}
