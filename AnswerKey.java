public class AnswerKey
{
	public String question;
	public int correctAnswerIndex;
	public String[] answers;
	
	public AnswerKey(String question, int correctAnswerIndex, String... answers)
	{
		this.question = question;
		this.correctAnswerIndex = correctAnswerIndex;
		this.answers = answers;
	}
}