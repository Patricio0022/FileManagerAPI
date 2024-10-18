import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

export function InputButton() {
  const [url, setUrl] = useState('');

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUrl(event.target.value);
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try {
      const response = await fetch(`http://localhost:8080/api/files/file?url=${encodeURIComponent(url)}`, {
        headers: {
          'Accept': 'application/json'
        }
      });
      const data = await response.text();
      console.log(data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  return (
    <form className="flex w-full max-w-sm items-center space-x-2" onSubmit={handleSubmit}>
      <Input 
        type="text" 
        placeholder="https://jsonplaceholder.typicode.com/todos" 
        value={url}
        onChange={handleChange} 
      />
      <Button type="submit">Submit</Button>
    </form>
  );
}
